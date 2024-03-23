package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.Plant;
import persistence.PlantRepository;

@Configuration
public class DatabaseConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Bean
	public CommandLineRunner initDatabase(PlantRepository repository) {
		log.info("Preloading data");
		
		return args -> {
			repository.save(new Plant("Monstera Deliciosa", "Guest Room", null));
		};
	}
	
}
