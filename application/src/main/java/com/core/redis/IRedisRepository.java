package com.core.redis;

import com.core.data.impl.redis.RedisItem;

import java.util.Map;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
public interface IRedisRepository {
    Map<Object, Object> findAllMovies();
    RedisItem findMovie(String id);

    void add(RedisItem redisItem);
    void delete(String id);
}