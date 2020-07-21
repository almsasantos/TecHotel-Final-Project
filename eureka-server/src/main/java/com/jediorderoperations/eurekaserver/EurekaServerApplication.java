package com.jediorderoperations.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka MicroService Application
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	/**
	 * Main Method
	 * @param args receives a List of String
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
