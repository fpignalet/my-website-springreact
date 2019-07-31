package com.core.redis;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
public interface IRedisMessagePublisher {
    void publish(final String message);
}