package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

@Slf4j
@JsonRootName("boulotentrycontent")
public class CVboulotcontent extends JSONParser {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("field", field)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String field;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> subs;
}
