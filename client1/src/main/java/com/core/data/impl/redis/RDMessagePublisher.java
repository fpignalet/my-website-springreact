package com.core.data.impl.redis;

import com.core.data.IRDMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Service
public class RDMessagePublisher implements IRDMessagePublisher {

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

    public RDMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public RDMessagePublisher() {
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

}