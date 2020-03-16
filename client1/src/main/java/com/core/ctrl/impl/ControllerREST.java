package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.eng.EEngJSONFiles;
import com.core.eng.impl.EngServiceCom;
import com.core.eng.impl.EngServiceDBHistory;
import com.core.eng.impl.EngServiceDBTest;
import com.core.eng.impl.EngServiceTests;
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
    @CrossOrigin
    @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
    public String exthttpgetjson0() throws IOException {
        return engineDB.load(EEngJSONFiles.TEST);
    }

    /**
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @CrossOrigin
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    public String exthttpgetjson1() throws IOException {
        return engineHistory.load(EEngJSONFiles.HISTIN);
    }

    /**
     * to be tested in browser
     */
    @CrossOrigin
    @RequestMapping(value = "/extnativelib", method = RequestMethod.GET)
    public String extnativelib() throws IOException {
        extFacade.testSimple();
        extFacade.testSerial();
        //create JSON result from above
        final String result = "{ WILL BE THERE SOON }";
        return getResult("LIBRARY OOK", result);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(path = "/testactuator")
    public String testactuator(final HttpServletRequest request) {
        return engineTests.testActuator(request);
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/testmicroservicecomrest")
    public String testmicroservicecom(final HttpServletRequest request) throws IOException {
        return engineCom.testMicroserviceComREST(request);
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/testmicroservicecommq")
    public String testmicroservicecommq(final HttpServletRequest request) throws IOException {
        return engineCom.testMicroserviceComMQ(request);
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/testhttprequest")
    public String testhttprequest(final HttpServletRequest request) throws Exception {
        return engineCom.testSimpleHttpRequest();
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/testjms")
    public String testjms(final HttpServletRequest request) throws Exception {
        return engineCom.testJMSMail(request);
    }

    /************************************************************************
     INIT PART
     */

    @Autowired
    private EngServiceDBTest engineDB;

    @Autowired
    private EngServiceDBHistory engineHistory;

    @Autowired
    private EngServiceTests engineTests;

    @Autowired
    private EngServiceCom engineCom;

    @Autowired
    private BExtFacade extFacade;

}
