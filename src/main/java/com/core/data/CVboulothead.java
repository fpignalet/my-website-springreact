package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVboulothead")
public class CVboulothead extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVboulothead\":{" +
                        "\"boulotentrydate\":\"DATA\"," +
                        "\"boulotentryboite\":\"DATA\"," +
                        "\"boulotentrydesc\":\"DATA\"," +
                        "\"data_CVboulotitem\":{" +
                            "\"boulotentryitem\":\"DATA\"," +
                            "\"boulotentrytitle\":\"DATA\"," +
                            "\"boulotentrycontent\":\"DATA\"," +
                        "}" +
                    "}" +
                "}";
            parse(data, CVboulothead.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("boulotentrydate", boulotentrydate)
            .append("boulotentryboite", boulotentryboite)
            .append("boulotentrydesc", boulotentrydesc)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String boulotentrydate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String boulotentryboite;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String boulotentrydesc;
}
