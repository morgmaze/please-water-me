package persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.PlantSpeciesCareInformation;

@Repository
public interface PlantCareDatabase extends CrudRepository<PlantSpeciesCareInformation, Long> {
	
	@Query("SELECT p FROM PlantSpeciesCareInformation p WHERE p.species = :species")
	Optional<PlantSpeciesCareInformation> findBySpecies(String species);

}
