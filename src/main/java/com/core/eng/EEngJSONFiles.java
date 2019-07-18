package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * in resources/static/data:
 */
public enum EEngJSONFiles {

    TEST("datatest"),
    CVIN("datafpicv"),
    ADBOOKIN("dataadbook"),
    CVOUT("datafpicvout"),
    ADBOOKOUT("dataadbookout");

    @Getter(AccessLevel.PUBLIC)
    private String name;
    private EEngJSONFiles(final String name){
        this.name = dataRepo + name + postfix;
    }
    private static final String dataRepo = "src/main/resources/static/data/";
    private static final String postfix = ".json";
}
