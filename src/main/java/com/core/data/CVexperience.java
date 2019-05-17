package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVexperience")
public class CVexperience extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVexperience\":{" +
                        "\"exp_title\":\"DATA\"," +
                        "\"exp_content\":\"DATA\"" +
                    "}" +
                "}";
            parse(data, null, new Class[]{ CVexperience.class });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("exp_title", exp_title)
            .append("exp_content", exp_content)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String exp_title;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String exp_content;
}
