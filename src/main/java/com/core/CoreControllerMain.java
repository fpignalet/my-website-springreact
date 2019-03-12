package com.core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public class CoreControllerMain extends ACoreControllerBase {

    /**
     * @param engine1
     */
    public CoreControllerMain(EngService engine1) {
        super(engine1);
    }

    /**
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/")
    public String entrytest(
        @RequestParam(name="name", required=false, defaultValue="TOTO 1") String name,
        Model model)
    {
        model.addAttribute("name", name);
        log.info("OK");
        return "entrytest";
    }

}
