package com.core.redis.impl;

import com.core.data.impl.redis.RedisMovie;
import com.core.redis.IRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements IRedisRepository {

    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }
    public RedisMovie findMovie(final String id){
        return (RedisMovie) hashOperations.get(KEY, id);
    }

    public void add(final RedisMovie redisMovie) {
        hashOperations.put(KEY, redisMovie.getId(), redisMovie.getName());
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
