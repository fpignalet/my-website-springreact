package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@JsonRootName("boulotentryitems")
public class CVboulotitem extends JSONParser {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("boulotentryitem", boulotentryitem)
            .append("boulotentrytitle", boulotentrytitle)
            .append("boulotentrycontent", boulotentrycontent)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> boulotentryitem;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> boulotentrytitle;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> boulotentrycontent;
}
