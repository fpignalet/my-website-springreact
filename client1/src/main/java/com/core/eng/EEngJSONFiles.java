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
    private String name;
    private EEngJSONFiles(final String name){
        this.name = dataRepo + name + postfix;
    }
    public static final String dataRepo = "static/data/";
    private static final String postfix = ".json";
}
