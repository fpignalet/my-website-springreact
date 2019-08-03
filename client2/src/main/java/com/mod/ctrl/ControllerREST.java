package com.mod.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Some answers to http requests
 */
@Slf4j
@RestController
public class ControllerREST {

    /************************************************************************
     PUBLIC IMPLEM PART:
     */

    /**
     * @return the content of src/main/resources/static/datatest.json data file
     */
    @RequestMapping(value = "/client2test/sub/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public String client2test(@PathVariable Long id) {
        try {
            log.info("received message from client1");
            return getResult("MICROSERVICE OOK " + id + ", OOK");
        } catch (IOException e) {
            e.printStackTrace();
            return getError("MICROSERVICE NOT OOK!", e);
        }
    }

    /************************************************************************
     INNER IMPLEM PART:
     */
    /**
     * @return
     * @throws IOException
     */
    protected String getResult(final String message) throws IOException {
        return "{ \"result\": \"" + message + "\" }";
    }

    /**
     * @return
     * @throws IOException
     */
    protected String getError(final String message, final Exception e) {
        e.printStackTrace();
        return "{ \"error\": \"" + message + "\" }";
    }

}
