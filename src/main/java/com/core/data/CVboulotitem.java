package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVboulotitem")
public class CVboulotitem extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVboulotitem\":{" +
                        "\"boulotentryitem\":\"DATA\"," +
                        "\"boulotentrytitle\":\"DATA\"," +
                        "\"boulotentrycontent\":\"DATA\"," +
                    "}" +
                "}";
            parse(data, CVboulotitem.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    private String boulotentryitem;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String boulotentrytitle;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String boulotentrycontent;
}
