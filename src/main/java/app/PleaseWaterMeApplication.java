package app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "api")
@EntityScan("model")
@EnableJpaRepositories("persistence")
public class PleaseWaterMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PleaseWaterMeApplication.class, args);
	}

}
