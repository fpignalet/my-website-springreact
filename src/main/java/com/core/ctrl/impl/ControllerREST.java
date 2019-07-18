package com.core.ctrl.impl;

import com.core.async.AAsyncTasks;
import com.core.ctrl.AControllerBase;
import com.core.eng.EEngJSONFiles;
import com.core.eng.impl.EngServiceDBCV;
import com.core.eng.impl.EngServiceDBTest;
import com.core.ext.impl.BExtFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            return getEngineCV().load(EEngJSONFiles.CVIN);
        } catch (IOException e) {
            e.printStackTrace();
            return getError("JSON NOT OOK!", e);
        }
    }

    /**
     * to be tested in browser
     */
    @RequestMapping(value = "/extpopulatecvdb", method = RequestMethod.GET)
    @CrossOrigin
    public String extpopulatedb() {
        try {
            getEngineCV().fromItems2DB();
            return getResult("DB OOK");
        } catch (IOException e) {
            return getError("DB NOT OOK!", e);
        }
    }

    /**
     * to be tested in browser
     */
    @RequestMapping(value = "/extpopulatecvfile", method = RequestMethod.GET)
    @CrossOrigin
    public String extpopulatefile() {
        try {
            getEngineCV().fromDB2Items();
            return getResult("FILE OOK");
        } catch (IOException e) {
            return getError("FILE NOT OOK!", e);
        }
    }

    /**
     * to be tested in browser
     */
    @RequestMapping(value = "/extnativelib", method = RequestMethod.GET)
    @CrossOrigin
    public String extnativelib() {
        try {
            extFacade.testAll();
            return getResult("LIBRARY OOK");
        } catch (Exception e) {
            return getError("LIBRARY NOT OOK!", e);
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
        final EngServiceDBCV engineCV,
        final AAsyncTasks taskManager,
        final BExtFacade extFacade) {
        super(engineDB, engineCV, null, null);
        this.extFacade = extFacade;
    }

    @Autowired
    final BExtFacade extFacade;

}
