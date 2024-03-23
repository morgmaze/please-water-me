package persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Plant;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Long> {}
