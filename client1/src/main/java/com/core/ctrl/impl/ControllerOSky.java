package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.eng.impl.EngServiceOSky;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @CrossOrigin
    @RequestMapping(value="/openskyadsb_list", method = RequestMethod.GET)
    public String addressbook_list() throws Exception {
        serviceOSky.execute();
        log.info("ACTION OPENSKY DECODER SOMETHING...");
        return getResult(null, "");
    }

    /************************************************************************
     INIT PART
     */

    @Autowired
    private EngServiceOSky serviceOSky;

}
