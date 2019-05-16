package com.core.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * using thymeleaf fragments
 */
@Controller
@Slf4j
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerAlt extends AControllerBase {

    /**
     * in templates:
     */
    private final static String[] pageNames = {
        "entrywebsite"
    };

    /**
     */
    public ControllerAlt() {
        super(null, null, null);
    }

    /**
     * @return
     */
    @GetMapping("/alt")
    public String webtest(
            @RequestParam(name="name", required=false, defaultValue="WEB CONTROLER") String name,
            Model model)
    {
        model.addAttribute("name", name);
        log.info("OK");
        return pageNames[0];
    }

}
