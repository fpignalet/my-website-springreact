package com.core.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * This base controller is mostly a wrapper above Eng... services.
 */
@Slf4j
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public abstract class AControllerBase {

    /************************************************************************
     INNER IMPLEM PART:
     */

    /**
     * @return
     * @throws IOException
     */
    protected String getResult(final String message, final String data) throws IOException {
        if(null != message) {
            return "{ \"message\": \"" + message + "\", \"result\": " + data + " }";
        }
        else {
            return "{ \"result\": " + data + " }";
        }
    }

    /**
     * @return
     * @throws IOException
     */
    protected String getError(final Exception e) {
        log.debug(e.getStackTrace().toString());
        return "{ \"error\": \"" + e.getMessage() + "\" }";
    }

}
