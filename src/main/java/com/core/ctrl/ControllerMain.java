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
public class ControllerMain extends AControllerBase {

    /**
     * in templates:
     */
    private final static String[] pageNames = {
        "entrymain"
    };

    /**
     */
    public ControllerMain(final EngServiceDB engineDB) {
        super(engineDB, null, null);
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
        getEngineDB().updateModel(model);
        log.info("OK");
        return pageNames[0];
    }

}
