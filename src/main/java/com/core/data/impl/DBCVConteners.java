package com.core.data.impl;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief wrapper above a DBHistContener collection
 */
public class DBCVConteners extends ArrayList<DBHistContener> {

    @Getter(AccessLevel.PUBLIC)
    private static Class[] subItems = {
        DBHistContener.class,
        DBHistItem.class,
        DBHistContent.class,
        DBHistSub.class,
        DBHistText.class
    };

    /**
     * default constructor
     */
    public DBCVConteners() {}

    /**
     * constructor
     * @param itemsDB
     */
    public DBCVConteners(final List<DBHistContener> itemsDB) {
        super(itemsDB);
    }

}
