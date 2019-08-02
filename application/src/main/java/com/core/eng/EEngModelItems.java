package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

/**
 * @brief: to reference data in GUI
 */
@FieldNameConstants
public enum EEngModelItems {

    JSONHIST("JSONHIST"),
    JSONABOOK("JSONAB"),
    DBTEST("DB"),
    DBHIST("HIST"),
    DBBLOG("BLOG"),
    DBABOOK("ADBOOK");

    EEngModelItems(final String name){
        this.name = prefix + name;
    }
    @Getter(AccessLevel.PUBLIC)
    private String name;
    private final static String prefix = "model_";
}
