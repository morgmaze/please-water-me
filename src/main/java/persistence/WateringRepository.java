package persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import model.Watering;

public interface WateringRepository extends CrudRepository<Watering, Long> {
	
	List<Watering> findByPlantId(Long plantId);
	
	@Transactional
	void deleteByPlantId(long plantId);

}
