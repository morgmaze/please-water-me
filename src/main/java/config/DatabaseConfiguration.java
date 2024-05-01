package config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.FutureWatering;
import model.Plant;
import persistence.FutureWateringRepository;
import persistence.PlantRepository;

@Configuration
public class DatabaseConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Bean
	public CommandLineRunner initDatabase(PlantRepository plantRepository, FutureWateringRepository futureWateringRepository) {
		log.info("Preloading data");
		
		return args -> {
			// plant 1
			Plant plant1 = new Plant("Monstera Deliciosa", "Guest Room", null);
			plantRepository.save(plant1);
			
			plant1.setId(1L); // would have gotten id 1 after saving
			futureWateringRepository.save(new FutureWatering(plant1, new Date()));
			
			// plant 2
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Plant plant2 = new Plant("Snake Plant", "Dining Room", df.parse("2023-09-30"));
//			plantRepository.save(plant2);
//			plant2.setId(2L);
//			futureWateringRepository.save(new FutureWatering(plant2, df.parse("2024-05-15")));
			
		};
	}
	
}
