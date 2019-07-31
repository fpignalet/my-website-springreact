package com.core.redis;

import com.core.data.impl.redis.RedisMovie;

import java.util.Map;

public interface IRedisRepository {
    Map<Object, Object> findAllMovies();
    RedisMovie findMovie(String id);

    void add(RedisMovie redisMovie);
    void delete(String id);
}