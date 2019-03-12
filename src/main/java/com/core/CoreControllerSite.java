package com.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
@Controller
public class CoreControllerSite extends ACoreControllerBase {

    /**
     * @param engine1
     */
    public CoreControllerSite(EngService engine1) {
        super(engine1);
    }

    /**
     * @return
     */
    @GetMapping("/web")
    public String web() {
        log.info("OK");
        return "entrytest";
    }

}
