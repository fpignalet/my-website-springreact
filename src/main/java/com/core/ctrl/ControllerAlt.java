package com.core.ctrl;

import com.core.eng.EngServiceDB;
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
    public ControllerAlt(final EngServiceDB engineDB) {
        super(engineDB, null, null);
    }

    /**
     * @return
     */
    @GetMapping("/altentry")
    public String webtest(
            @RequestParam(name="name", required=false, defaultValue="WEB CONTROLER") String name,
            Model model)
    {
        model.addAttribute("name", name);
        return pageNames[0];
    }

}
