package com.core.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * using thymeleaf fragments
 */
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerWebsite extends AControllerBase {

    /**
     * in templates:
     */
    private final static String[] pageNames = {
        "entrywebsite"
    };

    /**
     */
    public ControllerWebsite() {
        super(null, null, null);
    }

    /**
     * @return
     */
    @GetMapping("/web")
    public String webtest(
            @RequestParam(name="name", required=false, defaultValue="WEB CONTROLER") String name,
            Model model)
    {
        updateModel(name, model);
        log.info("OK");
        return pageNames[0];
    }

    @Override
    protected void updateModel(String name, Model model) {
        model.addAttribute("name", name);
    }

    /**
     *
     */
    protected static Logger log = LoggerFactory.getLogger(ControllerWebsite.class);

}
