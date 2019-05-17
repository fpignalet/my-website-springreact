package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@JsonRootName("data_CVboulotitem")
public class JSONParser {

    public static<T> void parse(final String data, Class<T> root, Class[] subclasses) throws IOException {
        final ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        for(final Class subclasse: subclasses){
            om.registerSubtypes(subclasse);
        }
        final T instance = om.readValue(data, root);
        log.info(instance.toString());
    }

}
