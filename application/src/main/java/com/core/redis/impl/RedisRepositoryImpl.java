package com.core.redis.impl;

import com.core.data.impl.redis.RedisItem;
import com.core.redis.IRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Repository
public class RedisRepositoryImpl implements IRedisRepository {

    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }
    public RedisItem findMovie(final String id){
        return (RedisItem) hashOperations.get(KEY, id);
    }

    public void add(final RedisItem redisItem) {
        hashOperations.put(KEY, redisItem.getId(), redisItem.getName());
    }
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    private static final String KEY = "Movie";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
}
