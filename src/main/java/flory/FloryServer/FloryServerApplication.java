package flory.FloryServer;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class FloryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloryServerApplication.class, args);
	}

}
