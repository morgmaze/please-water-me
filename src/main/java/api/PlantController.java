package api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Plant;
import persistence.PlantRepository;

@RestController
public class PlantController {
	
	private final PlantRepository repository;
	
	public PlantController(PlantRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/plants")
	public Plant addPlant(@RequestBody Plant newPlant) {
		return repository.save(newPlant);
	}
	
	@GetMapping("/plants")
	public List<Plant> getPlants() {
		return (List<Plant>) repository.findAll();
	}
	
	@PutMapping("/plants/{id}")
	public Plant replacePlant(@PathVariable Long id, @RequestBody Plant newPlant) {
		// TODO: different implementation where i update the desired field(s), not replace
		// the whole thing
		
		return repository.findById(id).map(plant -> {
			// update all fields but id
			plant.setSpecies(newPlant.getSpecies());
			plant.setName(newPlant.getName());
			plant.setLocation(newPlant.getLocation());
			plant.setDateAcquired(newPlant.getDateAcquired());
			
			// save the updates
			return repository.save(plant);
		}).orElseGet(() -> {
			// create new plant with given id
			newPlant.setId(id);
			
			return repository.save(newPlant);
		});
	}
	
	@DeleteMapping("/plants/{id}")
	public void deletePlant(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
