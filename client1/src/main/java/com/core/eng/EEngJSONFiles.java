package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * in resources/static/data:
 */
public enum EEngJSONFiles {

    TEST("datatest"),
    TESTOUT("datatestout"),
    TESTJSON("jsontest"),
    TESTJSONOUT("jsontestout"),
    HISTIN("datafpihist"),
    HISTOUT("datafpihistout"),
    ADBOOKIN("dataadbook"),
    ADBOOKOUT("dataadbookout");

    @Getter(AccessLevel.PUBLIC)
    private final String name;
    EEngJSONFiles(final String name){
        this.name = dataRepo + name + postfix;
    }
    private static final String dataRepo = "static/data/";
    private static final String postfix = ".json";
}
