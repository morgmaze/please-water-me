package persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.FutureWatering;

@Repository
public interface FutureWateringRepository extends CrudRepository<FutureWatering, Long> {

	List<FutureWatering> findAll();
	
	Optional<FutureWatering> findByPlantId(Long plantId);
	
	void deleteByPlantId(Long plantId);
	
}
