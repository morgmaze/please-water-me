package persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Plant;

/**
 * JPA repository to store Plant objects.
 * 
 * @author morganmazer
 *
 */
@Repository
public interface PlantRepository extends CrudRepository<Plant, Long> {
	
	List<Plant> findAll();
	
	Optional<Plant> findById(Long id);
	
}
