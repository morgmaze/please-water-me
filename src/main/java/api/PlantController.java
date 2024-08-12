package api;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import model.FutureWatering;
import model.Plant;
import model.PlantSpeciesCareInformation;
import model.Watering;
import persistence.FutureWateringRepository;
import persistence.PlantCareDatabase;
import persistence.PlantRepository;
import persistence.WateringRepository;

@Controller
public class PlantController {
	
	private static final Logger log = LoggerFactory.getLogger(PlantController.class);
	
	@Autowired
	PlantRepository plantRepository;
	
	@Autowired
	WateringRepository wateringRepository;
	
	@Autowired
	FutureWateringRepository futureWateringRepository;
	
	@Autowired
	PlantCareDatabase plantCareDatabase;

	/**
	 * Add a plant.
	 * 
	 * @param newPlant the plant to add
	 * @param model the Model
	 * 
	 * @return the plant-added.html page
	 */
	@PostMapping("/plants")
	public String addPlant(@ModelAttribute("plant") Plant newPlant, Model model) {
		// persist to database
		Plant saved = plantRepository.save(newPlant);
		
		model.addAttribute("plant", saved);
		
		return "plant-added";
	}
	
	/**
	 * Retrieve all plants.
	 * 
	 * @param model the Model
	 * 
	 * @return the plants.html page
	 */
	@GetMapping("/plants")
	public String getAllPlants(Model model) {
		List<Plant> plants = (List<Plant>) plantRepository.findAll();
		
		model.addAttribute("plants", plants);
		
		return "plants";
	}
	
	/**
	 * Show the form to add a plant.
	 * 
	 * @param model the Model
	 * 
	 * @return the add-plant.html page
	 */
	@GetMapping("/addPlant")
	public String showAddPlantForm(Model model) {
		model.addAttribute("plant", new Plant());
		
		return "add-plant";
	}
	
	/**
	 * Get a plant by ID.
	 * 
	 * @param id the plant's id
	 * @param model the Model
	 * 
	 * @return the plant-details.html page
	 */
	@GetMapping("/plants/{id}")
	public String getPlantById(@PathVariable Long id, Model model) {
		Optional<Plant> optionalPlant = plantRepository.findById(id);
		if (optionalPlant.isPresent()) {
			Plant plant = optionalPlant.get();
			model.addAttribute("plant", plant);
			
			// look up care information for this species
			Optional<PlantSpeciesCareInformation> optionalCareInfo = plantCareDatabase.findBySpecies(plant.getSpecies());
			if (optionalCareInfo.isPresent()) {
				model.addAttribute("careInfo", optionalCareInfo.get());
			}
			
			// retrieve watering history for this plant
			List<Watering> wateringHistory = wateringRepository.findByPlantId(id);
			// add a list of the dates watered for display in the UI
			List<Date> datesWatered = wateringHistory.stream().map(w -> w.getDateWatered()).collect(Collectors.toList());
			datesWatered.sort((d1, d2) -> (d1.compareTo(d2)));
			// TODO: return only the 5 most recent waterings
			model.addAttribute("wateringHistory", datesWatered);
			
			// see if there is a watering reminder set for this plant
			Optional<FutureWatering> optionalFutureWatering = futureWateringRepository.findByPlantId(plant.getId());
			if (optionalFutureWatering.isPresent()) {
				model.addAttribute("futureWatering", optionalFutureWatering.get().getReminderDate());
			}
		}
		
		return "plant-detail";
	}
	
	// TODO: update
//	@PutMapping("/plants/{id}")
//	public Plant replacePlant(@PathVariable Long id, @RequestBody Plant newPlant) {
//		// TODO: different implementation where i update the desired field(s), not replace
//		// the whole thing
//		
//		return plantRepository.findById(id).map(plant -> {
//			// update all fields but id
//			plant.setSpecies(newPlant.getSpecies());
//			plant.setLocation(newPlant.getLocation());
//			plant.setDateAcquired(newPlant.getDateAcquired());
//			
//			// save the updates to the database
//			return plantRepository.save(plant);
//		}).orElseGet(() -> {
//			// create new plant with given id
//			newPlant.setId(id);
//			
//			return plantRepository.save(newPlant);
//		});
//	}
	
	/**
	 * Delete a plant by id.
	 * 
	 * @param id the id of the plant to delete
	 * @return the updated all plants page
	 */
	@DeleteMapping("/plants/{id}")
	public String deletePlant(@PathVariable Long id) {
		log.info("deleting plant with id " + id);
		
		plantRepository.deleteById(id);
		
		// go to all plants page
		return "redirect:/plants";
	}

}
