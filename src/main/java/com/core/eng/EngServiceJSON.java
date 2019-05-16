package com.core.eng;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 *
 */
@Service
@Slf4j
@ComponentScan({"com.core.data"})
public class EngServiceJSON implements IEngModelUpdater {

    /*************************************************************************
     MULTI-PURPOSE
     *************************************************************************/
    /**
     * @return
     */
    public String doReactTest() {
        return "TEST ENGINE1";
    }

    /*************************************************************************
     INTERFACE ENFORCING
     *************************************************************************/
    /**
     * @param model
     */
    @Override
    public void updateModel(final Model model) {
    }

    /*************************************************************************
     DATA TESTS
     *************************************************************************/
    /**
     * @param param
     * @return
     */
    public String getAnswerJSON(final String param) {
        try {
            final HashMap<String, String> result = new HashMap<String, String>();
            result.put("item1", param);

            final String jsonstr = new ObjectMapper().writeValueAsString(result);

            return jsonstr;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * @param fileName
     * @return
     */
    public String doLoadJSON(final String fileName) {
        try {
            final Path path = Paths.get(fileName);
            final Charset charset = StandardCharsets.UTF_8;

            final String jsonstr =
                    new String(Files.readAllBytes(path))
                    .substring("module.exports = ".length())
                    .replace(";", "");

            return jsonstr;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /*************************************************************************
     INIT PART
     *************************************************************************/
    /**
     */
    public EngServiceJSON() {
        log.info("OK");
    }

}
