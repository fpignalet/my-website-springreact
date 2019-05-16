package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVskillsentry")
public class CVskillsentry extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVskillsentry\":{" +
                        "\"mskills_desc\":\"DATA\"," +
                        "\"mskills_level\":\"DATA\"," +
                        "\"mskills_text\":\"DATA\"" +
                    "}" +
                "}";
            parse(data, CVskillsentry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mskills_desc", mskills_desc)
                .append("mskills_level", mskills_level)
                .append("mskills_text", mskills_text)
                .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String mskills_desc;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String mskills_level;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String mskills_text;

}
