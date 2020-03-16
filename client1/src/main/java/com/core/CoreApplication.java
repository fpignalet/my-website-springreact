package com.core;

import com.core.config.CoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@EnableJms
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({CoreProperties.class})
//public class CoreApplication extends SpringBootServletInitializer {
public class CoreApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

/*
        try {
            BrokerService broker = new BrokerService();
            broker.addConnector("tcp://localhost:61616");
            broker.setUseJmx(true);
            ActiveMQDestination q = ActiveMQDestination.createDestination("testQueue", ActiveMQDestination.QUEUE_TYPE);
            ActiveMQDestination t = ActiveMQDestination.createDestination("testTopic", ActiveMQDestination.TOPIC_TYPE);
            broker.setDestinations(new ActiveMQDestination[] { q, t });
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

/*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CoreApplication.class);
    }
*/

}
