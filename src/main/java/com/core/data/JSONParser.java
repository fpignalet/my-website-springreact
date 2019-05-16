package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@JsonRootName("data_CVboulotitem")
public class JSONParser {

    public static<T> void parse(final String data, Class<T> classe) throws IOException {
        final ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        final T instance = om.readValue(data, classe);
        log.info(instance.toString());
    }

}
