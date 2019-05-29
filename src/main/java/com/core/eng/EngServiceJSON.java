package com.core.eng;

import com.core.data.impl.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JSON Operations
 */
@Service
@Slf4j
@ComponentScan({"com.core.data"})
public class EngServiceJSON implements IEngModelUpdater {

    /************************************************************************
     MULTI-PURPOSE
     */
    /**
     * @return
     */
    public String doReactTest() {
        return "TEST ENGINE1";
    }

    /************************************************************************
     INTERFACE ENFORCING
     */
    /**
     * in resources/templates/fragments:
     */
    private final static String[] modelItems = {
            "database" //for contentXXX.html
    };

    /**
     * @param model
     */
    @Override
    public void updateModel(final Model model) throws IOException {
        final String data = doLoadJSON(getDataRepo() + getFileNames()[1]);

        final DBConteners conteners = (DBConteners) parse(data,
                DBConteners.class,
                new Class[]{
                        DBHistContener.class,
                        DBHistItem.class,
                        DBHistContent.class,
                        DBHistSub.class,
                        DBHistText.class
                }
        );

        model.addAttribute(modelItems[0], conteners.toArray(new DBHistContener[0]));
    }

    /**
     * @param itemsDB
     * @throws IOException
     */
    public void updateFile(final List<DBHistContener> itemsDB) throws IOException {
        final String jsonString = fill(itemsDB);

        doSaveJSON(getDataRepo() + getFileNames()[2], jsonString);
    }

    /*************************************************************************
     DATA ACCESS
     *************************************************************************/
    public static class DBConteners extends ArrayList<DBHistContener> {}

    /**
     * @param param
     * @return
     */
    public String createAnswerJSON(final String param) {
        try {
            final HashMap<String, String> result = new HashMap<>();
            result.put("item1", param);

            final ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(result);
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

            return
                new String(Files.readAllBytes(path))
                .substring("module.exports = ".length())
                .replace(";", "");
        }
        catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * @param fileName
     * @param jsonString
     * @return
     */
    public void doSaveJSON(final String fileName, final String jsonString) {
        try {
            final PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(jsonString);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data
     * @param root
     * @param subclasses
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> ArrayList<?> parse(final String data, final Class<T> root, final Class[] subclasses) throws IOException {
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

    public <T> String fill(final List<T> itemsDB) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(itemsDB);
    }

    /************************************************************************
     INIT PART
     */
    /**
     */
    public EngServiceJSON() {
        log.info("OK");
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    private static final String dataRepo = "src/main/resources/static/";
    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    private static final String[] fileNames = {
            "datatest.js",
            "datafpicv.js",
            "datafpitest.json"
    };

}
