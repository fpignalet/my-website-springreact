package com.srv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class SrvApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SrvApplication.class, args);
	}

}
