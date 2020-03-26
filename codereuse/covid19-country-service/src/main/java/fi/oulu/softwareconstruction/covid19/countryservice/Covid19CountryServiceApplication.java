package fi.oulu.softwareconstruction.covid19.countryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Covid19CountryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19CountryServiceApplication.class, args);
	}

}
