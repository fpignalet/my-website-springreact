package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.eng.impl.EngServiceOSky;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * used from React module module_adbook.jsx
 */
@Slf4j
@RestController
@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
public class ControllerOSky extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART:
     */

    @RequestMapping(value="/openskyadsb_list", method = RequestMethod.GET)
    @CrossOrigin
    public String addressbook_list() {
        try {
            serviceOSky.execute();
            log.info("ACTION OPENSKY DECODER SOMETHING...");
            return getResult(null);
        } catch (Exception e) {
            return getError(e);
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
    protected String getError(final Exception e) {
        log.debug(e.getStackTrace().toString());
        return "{ \"error\": \"" + e.getMessage() + "\" }";
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @param engine
     */
    public ControllerOSky(final EngServiceOSky engine) {
        super(null, null, null, null, null);
        this.serviceOSky = engine;
    }

    @Autowired
    private final EngServiceOSky serviceOSky;
}
