package config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.FutureWatering;
import model.Plant;
import model.PlantSpeciesCareInformation;
import persistence.FutureWateringRepository;
import persistence.PlantCareDatabase;
import persistence.PlantRepository;

@Configuration
public class DatabaseConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Bean
	public CommandLineRunner initDatabase(PlantRepository plantRepository,
			FutureWateringRepository futureWateringRepository, PlantCareDatabase plantCareDatabase) {
		log.info("Preloading data");
		
		return args -> {
			// plant 1
			Plant plant1 = new Plant("Monstera deliciosa", "Guest Room", null);
			plant1 = plantRepository.save(plant1);
			
			futureWateringRepository.save(new FutureWatering(plant1, new Date()));
			
			// plant 2
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Plant plant2 = new Plant("Snake plant", "Dining Room", df.parse("2023-09-30"));
			plant2 = plantRepository.save(plant2);
			futureWateringRepository.save(new FutureWatering(plant2, df.parse("2024-05-15")));
			
			// populate plant_care_info database
			plantCareDatabase.save(new PlantSpeciesCareInformation("Monstera deliciosa", "Swiss cheese plant", 7, 14,
					"Medium to bright indrect", 65, 85, 18, 30, false, false));
			plantCareDatabase.save(new PlantSpeciesCareInformation("Snake plant", "N/A", 14, 56,
					"Partial sun", 60, 75, 16, 24, false, false));
			plantCareDatabase.save(new PlantSpeciesCareInformation("Peace lily", "N/A", 7, 14,
					"Medium to bright indirect", 65, 80, 18, 27, false, false));
			plantCareDatabase.save(new PlantSpeciesCareInformation("Aloe vera", "N/A", 7, 10,
					"Bright indirect", 55, 85, 13, 29, false, false));
			plantCareDatabase.save(new PlantSpeciesCareInformation("Spider plant", "N/A", 7, 10,
					"Bright indirect", 55, 80, 13, 27, true, true));
		};
	}
	
}
