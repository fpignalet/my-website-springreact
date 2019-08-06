package com.core.data;

import com.core.data.impl.redis.RDItem;

import java.util.Map;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
public interface IRDRepository {
    Map<Object, Object> findAllMovies();
    RDItem findMovie(String id);

    void add(RDItem rdItem);
    void delete(String id);
}