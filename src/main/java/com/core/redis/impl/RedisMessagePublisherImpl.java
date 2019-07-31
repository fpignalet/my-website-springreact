package com.core.redis.impl;

import com.core.redis.IRedisMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Service
public class RedisMessagePublisherImpl implements IRedisMessagePublisher {

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

    public RedisMessagePublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public RedisMessagePublisherImpl() {
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

}