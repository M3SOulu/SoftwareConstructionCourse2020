package fi.oulu.softwareconstruction.covid19.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Covid19DiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19DiscoveryServerApplication.class, args);
	}

}
