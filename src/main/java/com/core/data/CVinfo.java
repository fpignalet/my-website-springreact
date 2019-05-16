package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVinfo")
public class CVinfo extends JSONParser {

    public static void main(String[] args) {
        try {
            final String data = "{" +
                    "\"data_CVinfo\":{" +
                        "\"info_raisonsociale\":\"DATA\"," +
                        "\"info_adresse\":\"DATA\"," +
                        "\"info_email\":\"DATA\"," +
                        "\"info_phonenum\":\"DATA\"," +
                        "\"info_geburstag\":\"DATA\"" +
                    "}" +
                "}";
            parse(data, CVinfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("info_raisonsociale", info_raisonsociale)
            .append("info_adresse", info_adresse)
            .append("info_email", info_email)
            .append("info_phonenum", info_phonenum)
            .append("info_geburstag", info_geburstag)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String info_raisonsociale;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String info_adresse;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String info_email;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String info_phonenum;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String info_geburstag;
}
