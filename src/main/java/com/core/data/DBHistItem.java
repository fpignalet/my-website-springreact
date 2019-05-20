package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

@Slf4j
@JsonRootName("entryitems")
public class DBHistItem extends DBJson2Pojo {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("entryitem", entryitem).append("\n", "")
            .append("entrytitle", entrytitle).append("\n", "")
            .append("entrycontent", entrycontent).append("\n", "")
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entryitem;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entrytitle;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<DBHistContent> entrycontent;
}
