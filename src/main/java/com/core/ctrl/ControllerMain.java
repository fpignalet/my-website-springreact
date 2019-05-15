package com.core.ctrl;

import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import com.core.eng.EngServiceMail;
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
public class ControllerMain extends AControllerBase {

    /**
     *
     */
    private final static String[] pageNames = {
        "entrymain"
    };

    /**
     */
    public ControllerMain() {
        super(null, null, null);
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/")
    public String entrytest(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        model.addAttribute("name", name);
        log.info("OK");
        return pageNames[0];
    }

    /**
     *
     */
    protected static Logger log = LoggerFactory.getLogger(ControllerMain.class);

}
