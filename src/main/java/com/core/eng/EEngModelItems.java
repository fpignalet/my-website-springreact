package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

/**
 * @brief: to reference data in GUI
 */
@FieldNameConstants
public enum EEngModelItems {

    JSONCV("JSONCV"),
    JSONABOOK("JSONAB"),
    DBTEST("DB"),
    DBCV("CV"),
    DBABOOK("ADBOOK");

    EEngModelItems(final String name){
        this.name = prefix + name;
    }
    @Getter(AccessLevel.PUBLIC)
    private String name;
    private final static String prefix = "model_";
}
