package com.core.data;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
public interface IRDMessagePublisher {
    void publish(final String message);
}