package com.core.ctrl;

import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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
    public ControllerMain(final EngServiceDB engineDB, final EngServiceJSON engineJSON) {
        super(engineDB, engineJSON, null);
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
        try {
            model.addAttribute("name", name);
            getEngineDB().updateModel(model);
            getEngineJSON().updateModel(model);
            return pageNames[0];
        } catch (IOException e) {
            e.printStackTrace();
            return pageNames[0];
        }
    }

}
