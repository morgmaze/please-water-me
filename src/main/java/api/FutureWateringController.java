package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.FutureWatering;
import persistence.FutureWateringRepository;

@Controller
public class FutureWateringController {
	
	@Autowired
	FutureWateringRepository futureWateringRepository;
	
	
	@GetMapping("/futureWaterings")
	public String getFutureWaterings(Model model) {
		List<FutureWatering> futureWaterings = (List<FutureWatering>) futureWateringRepository.findAll();
		model.addAttribute("futureWaterings", futureWaterings);
		
		return "future-waterings";
	}

}
