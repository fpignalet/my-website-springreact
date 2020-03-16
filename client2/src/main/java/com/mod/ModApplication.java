package com.mod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@EnableAsync
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
//public class ModApplication extends SpringBootServletInitializer {
public class ModApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ModApplication.class, args);
	}

/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ModApplication.class);
	}
*/

}
