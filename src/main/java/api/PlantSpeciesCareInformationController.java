package api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.PlantSpeciesCareInformation;
import persistence.PlantCareDatabase;

@Controller
public class PlantSpeciesCareInformationController {
	
	@Autowired
	PlantCareDatabase plantCareDatabase;
	
	/**
	 * Retrieve care information for the given species.
	 * 
	 * @param species the plant species
	 * @param model the Model
	 * @return the care-info.html page with the {@link PlantSpeciesCareInformation}, if it exists
	 */
	@GetMapping("/plantCare")
	public String getPlantSpeciesCareInformation(@RequestParam String species, Model model) {
		Optional<PlantSpeciesCareInformation> optionalCareInfo = plantCareDatabase.findBySpecies(species);
		
		if (optionalCareInfo.isPresent()) {
			model.addAttribute("careInfo", optionalCareInfo.get());
		}
		
		return "care-info";
	}

}
