package com.core.data.impl.sql;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief wrapper above a DBContact collection
 */
public class DBAddressBook extends ArrayList<DBContact> {

    @Getter(AccessLevel.PUBLIC)
    private static final Class[] subItems = {
        DBContact.class
    };

    /**
     * default constructor
     */
    public DBAddressBook() {}

    /**
     * constructor
     * @param contacts
     */
    public DBAddressBook(final List<DBContact> contacts) {
        super(contacts);
    }
}
