package com.core.eng.impl;

import com.core.eng.EEngJSONFiles;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Everything to handle / manipulate JSON data files
 */
@Slf4j
public abstract class AEngJSONHandler {

    /*************************************************************************
     ABSTRACT LEVEL
     ************************************************************************/

    /**
     * @brief to load JSON data from a file
     * @return the content of a JSON file as a collection of items
     * @param <T> is the type of items to be loaded from file
     * @throws IOException
     */
    public abstract <T> ArrayList<?> loadFile() throws IOException;

    /**
     * @param itemsDB
     * @throws IOException
     */
    public abstract void updateFile(final ArrayList<?> itemsDB) throws IOException;

    /*************************************************************************
     DATA ACCESS
     ************************************************************************

    /**
     * @brief creates a JSON string from JSON file content
     * @param file contains the file to be loaded
     * @return a String containing the JSON data contained in file
     * @throws IOException
     */
    public String load(final EEngJSONFiles file) throws IOException {
        return load(file.getName());
    }

    /**
     * @brief creates a JSON string from itemsDB
     * @param itemsDB contains a collection of DB items
     * @param <T> is type of DB items
     * @return a JSON string
     * @throws IOException
     */
    public <T> String fill(final List<T> itemsDB) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(itemsDB);
    }

    /*************************************************************************
     INTERNAL
     *************************************************************************/
    /**
     * @brief load a file content
     * @param fileName contains the name of the file to be loaded
     * @return a string containing JSON data
     */
    protected String load(final String fileName) throws IOException {
        final Resource resource = new ClassPathResource(fileName);
        final Charset charset = StandardCharsets.UTF_8;
        return new String(Files.readAllBytes(resource.getFile().toPath()), charset);
    }

    /**
     * @brief Transform JSON data into Java classes
     * @param data contains JSON data to be transformed as Java classes
     * @param root contains the JSON data root key
     * @param subclasses is a list of needed Java sub classes
     * @param <T> is the type of the Java root class
     * @return an ArrayList of Java classes
     * @throws IOException when it fails
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
     * @param response
     * @return
     * @throws IOException
     */
    protected String parse(final ResponseEntity<String> response) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        return root.toString();
    }

    /**
     * @param fileName the name of the file
     * @param jsonString the data to be written
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
     * @brief constructor
     */
    public AEngJSONHandler() {
        log.info("OK");
    }

}
