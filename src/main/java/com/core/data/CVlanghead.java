package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVlanghead")
public class CVlanghead extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVlanghead\":{" +
                        "\"lang_title\":\"DATA\"," +
                    "}" +
                "}";
            parse(data, null, new Class[]{ CVlanghead.class });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("lang_title", lang_title)
                .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String lang_title;

}
