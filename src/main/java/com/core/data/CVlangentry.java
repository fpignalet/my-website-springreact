package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVlangentry")
public class CVlangentry extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVlangentry\":{" +
                        "\"lang_desc\":\"DATA\"," +
                        "\"lang_level\":\"DATA\"," +
                        "\"lang_text\":\"DATA\"" +
                    "}" +
                "}";
            parse(data, CVlangentry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("lang_desc", lang_desc)
                .append("lang_level", lang_level)
                .append("lang_text", lang_text)
                .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String lang_desc;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String lang_level;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String lang_text;

}
