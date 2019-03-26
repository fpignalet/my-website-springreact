package com.core.ctrl;

import com.core.eng.EngService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerWebsite extends AControllerBase {

    /**
     * @param engine1
     */
    public ControllerWebsite(EngService engine1) {
        super(engine1);
    }

    /**
     * @return
     */
    @GetMapping("/web")
    public String web() {
        log.info("OK");
        return "entrywebsite";
    }

}
