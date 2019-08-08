package com.core.async;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @brief the available tests EngTasks
 */
public enum EAsyncItems {
    SPEEDY("speed"),
    STANDARD("standard"),
    SLOW("slow"),
    FIXED("fixed"),
    CRONED("croned");

    @Getter(AccessLevel.PUBLIC)
    private final String name;
    EAsyncItems(final String name){
        this.name = name;
    }
}
