package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

@Slf4j
@JsonRootName("entrycontent")
public class DBHistContent {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("entrydesc", entrydesc).append("\n", "")
            .append("entrysubs", entrysubs).append("\n", "")
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String entrydesc;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entrysubs;
}
