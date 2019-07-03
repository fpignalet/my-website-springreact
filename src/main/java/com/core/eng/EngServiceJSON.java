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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        final String data = load(getDataRepo() + getFileNames()[1]);

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

    /*************************************************************************
     DATA ACCESS
     *************************************************************************/
    /**
     * @param param
     * @return
     */
    public String createAnswerJSON(final String param) throws JsonProcessingException {
        final HashMap<String, String> result = new HashMap<>();
        result.put("item1", param);

        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(result);
    }

    /**
     * @return
     */
    public DBConteners load() throws IOException {
        final String data = load(getDataRepo() + getFileNames()[1]);
        return (DBConteners) parse(data,
            DBConteners.class,
            new Class[]{
                DBHistContener.class,
                DBHistItem.class,
                DBHistContent.class,
                DBHistSub.class
            }
        );
    }

    /**
     * @param itemsDB
     * @throws IOException
     */
    public void update(final List<DBHistContener> itemsDB) throws IOException {
        final String jsonString = fill(itemsDB);
        save(getDataRepo() + getFileNames()[2], jsonString);
    }

    /**
     * @param itemsDB
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> String fill(final List<T> itemsDB) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(itemsDB);
    }

    /**
     * @return
     */
    public String load(final int index) throws IOException {
        return load(getDataRepo() + getFileNames()[index]);
    }

    /**
     * @param fileName
     * @return
     */
    protected String load(final String fileName) throws IOException {
        final Path path = Paths.get(fileName);
        final Charset charset = StandardCharsets.UTF_8;

        return
            new String(Files.readAllBytes(path))
                .substring("module.exports = ".length())
                .replace(";", "");
    }

    /**
     * @param data
     * @param root
     * @param subclasses
     * @param <T>
     * @return
     * @throws IOException
     */
    protected <T> ArrayList<?> parse(final String data, final Class<T> root, final Class[] subclasses) throws IOException {
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

    /**
     * @param fileName
     * @param jsonString
     * @return
     */
    protected void save(final String fileName, final String jsonString) throws FileNotFoundException, UnsupportedEncodingException {
        final PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(jsonString);
        writer.close();
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
    private static final String dataRepo = "src/main/resources/static/data/";
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
