package com.core.redis;

public interface IRedisMessagePublisher {
    void publish(final String message);
}