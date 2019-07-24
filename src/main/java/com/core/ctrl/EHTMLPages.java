package com.core.ctrl;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @brief available html pages in resources/static/templates
 */
public enum EHTMLPages {

    DEFAULT("default"),
    ADBOOK("adbook"),
    CV("CV"),
    BLOG("BLOG"),
    REACT("react"),
    ANGULAR("angular"),
    ERROR("error");

    EHTMLPages(final String name){
        this.name = prefix + name + postfix;
    }
    @Getter(AccessLevel.PUBLIC)
    private String name;
    private final static String prefix = "frontend_";
    private final static String postfix = ".html";
}
