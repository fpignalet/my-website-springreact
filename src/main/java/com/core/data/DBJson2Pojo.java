package com.core.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class DBJson2Pojo {

    public static<T> ArrayList<?> parse(final String data, final Class<T> root, final Class[] subclasses) throws IOException {
        final ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(null != subclasses){
            for(final Class subclasse: subclasses){
                om.registerSubtypes(subclasse);
            }

        }

        return (ArrayList<?>) om.readValue(data, root);
    }

}
