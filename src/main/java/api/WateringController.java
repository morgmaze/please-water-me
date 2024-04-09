package api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/water")
	public String recordWatering(@ModelAttribute("watering") Watering newWatering, Model model) {
		logger.info("record watering");
		
		// save to database
		wateringRepository.save(newWatering);
		
		// TODO: fix
		
		return "watering";
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
