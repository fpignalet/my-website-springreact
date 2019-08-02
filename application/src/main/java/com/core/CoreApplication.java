package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableAsync
@EnableScheduling
//@EnableEurekaServer
@SpringBootApplication
@EnableConfigurationProperties({CoreProperties.class})
public class CoreApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
