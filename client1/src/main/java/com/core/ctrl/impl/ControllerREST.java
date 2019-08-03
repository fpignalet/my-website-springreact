package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.eng.EEngJSONFiles;
import com.core.eng.impl.EngServiceDBHistory;
import com.core.eng.impl.EngServiceDBTest;
import com.core.eng.impl.EngServiceTests;
import com.core.ext.impl.BExtFacade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
            return getError(e);
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
            return getError(e);
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
            extFacade.testSerial();
            return getResult("LIBRARY OOK", "");
        } catch (Exception e) {
            return getError(e);
        }
    }

    @RequestMapping(path = "/testactuator")
    public String testactuator(final HttpServletRequest request) {
        return getEngineTests().testActuator(request);
    }

    @RequestMapping(path = "/testmicroservicecomrest")
    public String testmicroservicecom(HttpServletRequest request) throws IOException {
        return getEngineTests().testMicroserviceComREST(request);
    }

    @RequestMapping(path = "/testmicroservicecommq")
    public String testmicroservicecommq(HttpServletRequest request) throws IOException {
        return getEngineTests().testMicroserviceComMQ(request);
    }

    /************************************************************************
     INIT PART
     */

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceDBTest engineDB;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceDBHistory engineHistory;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceTests engineTests;

    @Autowired
    private BExtFacade extFacade;

}
