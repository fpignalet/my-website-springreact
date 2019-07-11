package com.core.data.impl;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;

public class DBConteners extends ArrayList<DBHistContener> {

    @Getter(AccessLevel.PUBLIC)
    private static Class[] subItems = {
        DBHistContener.class,
        DBHistItem.class,
        DBHistContent.class,
        DBHistSub.class,
        DBHistText.class
    };

}
