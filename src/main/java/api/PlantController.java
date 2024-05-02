package api;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.Plant;
import model.Watering;
import persistence.PlantRepository;
import persistence.WateringRepository;

@Controller
public class PlantController {
	
	private static final Logger log = LoggerFactory.getLogger(PlantController.class);
	
	@Autowired
	PlantRepository plantRepository;
	
	@Autowired
	WateringRepository wateringRepository;

	/**
	 * Add a plant.
	 * 
	 * @param newPlant
	 * @param model
	 * 
	 * @return the plant-added.html page
	 */
	@PostMapping("/plants")
	public String addPlant(@ModelAttribute("plant") Plant newPlant, Model model) {
		log.info("addPlant");
		
		// persist to database
		plantRepository.save(newPlant);
		
		model.addAttribute("plant", newPlant);
		// TODO: date format wrong
		
		return "plant-added";
	}
	
	/**
	 * Retrieve all plants.
	 * 
	 * @param model
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
	 * @return the plant-details.html page
	 */
	@GetMapping("/plants/{id}")
	public String getPlantById(@PathVariable Long id, Model model) {
		Optional<Plant> optionalPlant = plantRepository.findById(id);
		if (optionalPlant.isPresent()) {
			Plant plant = optionalPlant.get();
			model.addAttribute("plant", plant);
		}
		
		// retrieve watering history for this plant
		List<Watering> wateringHistory = wateringRepository.findByPlantId(id);
		// add a list of the dates watered for display in the UI
		List<Date> datesWatered = wateringHistory.stream().map(w -> w.getDateWatered()).collect(Collectors.toList());
		model.addAttribute("wateringHistory", datesWatered);
		
		return "plant-detail";
	}
	
	// TODO: update
	@PutMapping("/plants/{id}")
	public Plant replacePlant(@PathVariable Long id, @RequestBody Plant newPlant) {
		// TODO: different implementation where i update the desired field(s), not replace
		// the whole thing
		
		return plantRepository.findById(id).map(plant -> {
			// update all fields but id
			plant.setSpecies(newPlant.getSpecies());
			plant.setLocation(newPlant.getLocation());
			plant.setDateAcquired(newPlant.getDateAcquired());
			
			// save the updates to the database
			return plantRepository.save(plant);
		}).orElseGet(() -> {
			// create new plant with given id
			newPlant.setId(id);
			
			return plantRepository.save(newPlant);
		});
	}
	
	// TODO: update
	@DeleteMapping("/plants/{id}")
	public void deletePlant(@PathVariable Long id) {
		plantRepository.deleteById(id);
	}

}
