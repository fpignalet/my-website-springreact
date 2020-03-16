package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * in resources/static/data:
 */
public enum EEngTextFiles {

    TEST("oskytest");

    @Getter(AccessLevel.PUBLIC)
    private final String name;
    EEngTextFiles(final String name){
        this.name = dataRepo + name + postfix;
    }
    private static final String dataRepo = "static/data/";
    private static final String postfix = ".txt";
}
