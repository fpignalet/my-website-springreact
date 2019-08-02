package com.core.data.impl.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
public class RedisItem implements Serializable {
    public RedisItem(String key, String value) {
        id = key;
        name = value;
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String id;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String name;

}
