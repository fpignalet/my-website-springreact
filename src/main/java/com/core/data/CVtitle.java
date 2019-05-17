package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVtitle")
public class CVtitle extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVtitle\":{" +
                        "\"moi_photo\":\"DATA\"," +
                        "\"moi_name\":\"DATA\"" +
                    "}" +
                "}";
            parse(data, null, new Class[]{ CVtitle.class });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("moi_photo", moi_photo)
                .append("moi_name", moi_name)
                .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String moi_photo;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String moi_name;

}
