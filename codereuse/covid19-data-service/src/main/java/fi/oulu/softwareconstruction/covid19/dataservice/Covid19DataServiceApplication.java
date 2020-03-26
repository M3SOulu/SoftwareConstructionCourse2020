package fi.oulu.softwareconstruction.covid19.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class Covid19DataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19DataServiceApplication.class, args);
	}

}
