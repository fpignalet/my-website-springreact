package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CoreApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
