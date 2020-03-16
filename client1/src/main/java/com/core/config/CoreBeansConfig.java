package com.core.config;

import com.core.data.IMQRepository;
import com.core.data.impl.mq.MQTransaction;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.client.RestTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class CoreBeansConfig {

    @Bean
    RestTemplate getRestTemplate() { return new RestTemplate(); }

    @Bean
    IMQRepository getOrderTransactionRepository() {
        return new IMQRepository() {
            @Override
            public void save(final MQTransaction transaction) {}
        };
    }

    @Bean
    public JmsListenerContainerFactory<?> getJmsFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean
    // Serialize message content to json using TextMessage
    public MessageConverter getJacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
