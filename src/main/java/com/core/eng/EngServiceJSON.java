package com.core.eng;

import com.core.data.DBItem1;
import com.core.data.DBItem1DAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 *
 */
@Service
@ComponentScan({"com.core.data"})
public class EngServiceJSON {

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

    /**
     *
     */
    private static Logger log = LoggerFactory.getLogger(EngServiceJSON.class);

}
