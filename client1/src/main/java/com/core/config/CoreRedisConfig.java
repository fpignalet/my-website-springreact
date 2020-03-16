package com.core.config;

import com.core.data.IRDMessagePublisher;
import com.core.data.impl.redis.RDMessagePublisher;
import com.core.data.impl.redis.RDMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Configuration
public class CoreRedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    IRDMessagePublisher redisPublisher() {
        return new RDMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RDMessageSubscriber());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("TEST");
    }

}
