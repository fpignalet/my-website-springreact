package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@JsonRootName("data_CVtitle")
public class CVtitle {

    public static void main(String[] args) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            System.out.println(
                om.readValue(
                    "{\"data_CVtitle\":{\"moi_photo\":\"PHOTO\",\"moi_name\":\"NAME\"}}",
                    CVtitle.class
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "data_CVtitle [name=" + moi_photo + ", age=" + moi_name + "]";
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String moi_photo;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Integer moi_name;

}
