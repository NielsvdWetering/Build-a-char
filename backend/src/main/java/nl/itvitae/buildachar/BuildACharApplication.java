package nl.itvitae.buildachar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuildACharApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BuildACharApplication.class);
		application.setBanner(new CustomBanner());
		application.run(args);
	}

}
