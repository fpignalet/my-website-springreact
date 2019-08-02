package com.core.ctrl.impl;

import com.core.async.AAsyncTasks;
import com.core.ctrl.AControllerBase;
import com.core.eng.EEngJSONFiles;
import com.core.eng.impl.EngServiceDBHistory;
import com.core.eng.impl.EngServiceDBTest;
import com.core.ext.impl.BExtFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Some answers to http requests
 */
@Slf4j
@RestController
public class ControllerREST extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART:
     */

    /**
     * @return the content of src/main/resources/static/datatest.json data file
     */
    @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson0() {
        try {
            return getEngineDB().load(EEngJSONFiles.TEST);
        } catch (IOException e) {
            e.printStackTrace();
            return getError("JSON NOT OOK!", e);
        }
    }

    /**
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson1() {
        try {
            return getEngineHistory().load(EEngJSONFiles.HISTIN);
        } catch (IOException e) {
            e.printStackTrace();
            return getError("JSON NOT OOK!", e);
        }
    }

    /**
     * to be tested in browser
     */
    @RequestMapping(value = "/extnativelib", method = RequestMethod.GET)
    @CrossOrigin
    public String extnativelib() {
        try {
            extFacade.testSimple();
            Runtime.getRuntime().exec("socat pty,raw,echo=0,link=~/dev/fakesrd0 pty,raw,echo=0,link=~/dev/fakesrd1\n");
            new Thread(() -> {
                extFacade.testSerialOut();
            }).start();
/*
            new Thread(() -> {
                extFacade.testSerialIn();
            }).start();
*/
            return getResult("LIBRARY OOK");
        } catch (Exception e) {
            return getError("LIBRARY NOT OOK!", e);
        }
    }

    @RequestMapping(path = "/testactuator")
    public String home(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String host = request.getServerName();

        // Spring Boot >= 2.0.0.M7
        String endpointBasePath = "/actuator";
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");
        sb.append("</ul>");

        return sb.toString();
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
        return "{ \"result\": \"" + message + "\" }";
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param engineDB
     * @param engineCV
     * @param taskManager
     * @param extFacade
     */
    public ControllerREST(
        final EngServiceDBTest engineDB,
        final EngServiceDBHistory engineCV,
        final AAsyncTasks taskManager,
        final BExtFacade extFacade
    ) {
        super(engineDB, engineCV, null, null);
        this.extFacade = extFacade;
    }

    @Autowired
    final BExtFacade extFacade;

}
