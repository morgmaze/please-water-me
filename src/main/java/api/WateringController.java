package api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Plant;
import model.Watering;
import persistence.PlantRepository;
import persistence.WateringRepository;

@Controller
public class WateringController {
	
	private static final Logger logger = LoggerFactory.getLogger(WateringController.class);
	
	@Autowired
	WateringRepository wateringRepository;
	
	@Autowired
	PlantRepository plantRepository;
	
	/**
	 * Log a watering for a plant.
	 * 
	 * @param newWatering
	 * @param model
	 * @return
	 */
	@PostMapping("/water")
	public String recordWatering(@ModelAttribute("watering") Watering newWatering, Model model) {
		// save to database
		wateringRepository.save(newWatering);
		
		// add it to the model
		model.addAttribute("newWatering", newWatering);
		model.addAttribute("plant", newWatering.getPlant());
		
		return "watering-history";
	}
	
	@GetMapping("/water/history")
	public List<Watering> getWateringsForPlant(@RequestParam Long plantId) {
		// get the watering history for the given plant
		List<Watering> waterings = wateringRepository.findByPlantId(plantId);
		
		return waterings;
	}
	
	/**
	 * Show the form to record a watering.
	 * 
	 * @return the record-watering.html page
	 */
	@GetMapping("/recordWatering")
	public String showrecordWateringForm(Model model) {
		model.addAttribute("watering", new Watering());
		
		// get all plants to populate drop down
		List<Plant> plants = plantRepository.findAll();
		model.addAttribute("plants", plants);
		
		return "record-watering";
	}

}
