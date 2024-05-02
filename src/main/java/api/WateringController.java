package api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.Constants;
import model.FutureWatering;
import model.Plant;
import model.PlantSpeciesCareInformation;
import model.Watering;
import persistence.FutureWateringRepository;
import persistence.PlantCareDatabase;
import persistence.PlantRepository;
import persistence.WateringRepository;

@Controller
public class WateringController {
	
	private static final Logger logger = LoggerFactory.getLogger(WateringController.class);
	
	@Autowired
	WateringRepository wateringRepository;
	
	@Autowired
	PlantRepository plantRepository;
	
	@Autowired
	FutureWateringRepository futureWateringRepository;
	
	@Autowired
	PlantCareDatabase plantCareDatabase;
	
	
	/**
	 * Log a watering for a plant.
	 * 
	 * @param newWatering
	 * @param model
	 * @return
	 */
	@PostMapping("/water")
	public String recordWatering(@ModelAttribute("watering") Watering newWatering, Model model) {
		logger.info("recordWatering");
		
		// save to database
		Watering watering = wateringRepository.save(newWatering);
		
		/* schedule the next watering */
		FutureWatering futureWatering = new FutureWatering();
		futureWatering.setPlant(watering.getPlant());
		
		// calculate reminder date based on species (if we have the information), or use the default
		int daysUntilReminder = Constants.REMINDER_TIMEFRAME;
		Optional<PlantSpeciesCareInformation> optionalPlantCareInfo = plantCareDatabase.findBySpecies(watering.getPlant().getSpecies());
		if (optionalPlantCareInfo.isPresent()) {
			PlantSpeciesCareInformation careInformation = optionalPlantCareInfo.get();
			if (careInformation.getMinDaysToWater() > 0) {
				daysUntilReminder = careInformation.getMinDaysToWater();
			}
		}
		
		// set the reminder
		futureWatering.setReminderDate(java.sql.Date.valueOf(LocalDate.now().plusDays(daysUntilReminder)));
		FutureWatering savedFutureWatering = futureWateringRepository.save(futureWatering);
		logger.info("set future watering: " + futureWatering.toString());
		
		// add everything to the model
		model.addAttribute("newWatering", watering);
		model.addAttribute("plant", newWatering.getPlant());
		model.addAttribute("futureWatering", savedFutureWatering.getReminderDate());
		
		return "watering-history";
	}
	
	// TODO: can't access in browser
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
