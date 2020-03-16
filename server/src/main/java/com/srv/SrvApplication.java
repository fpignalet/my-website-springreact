package com.srv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
//public class SrvApplication extends SpringBootServletInitializer {
public class SrvApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SrvApplication.class, args);
	}

/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SrvApplication.class);
	}
*/

}
