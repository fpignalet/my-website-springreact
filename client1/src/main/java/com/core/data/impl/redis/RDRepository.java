package com.core.data.impl.redis;

import com.core.data.IRDRepository;
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
public class RDRepository implements IRDRepository {

    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }
    public RDItem findMovie(final String id){
        return (RDItem) hashOperations.get(KEY, id);
    }

    public void add(final RDItem rdItem) {
        hashOperations.put(KEY, rdItem.getId(), rdItem.getName());
    }
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    @Autowired
    public RDRepository(RedisTemplate<String, Object> redisTemplate){
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
