package com.core.ctrl;

import com.core.data.DBItem1;
import com.core.eng.EngServiceDB;
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
        updateModel(name, model);
        log.info("OK");
        return pageNames[0];
    }

    /**
     * @param name
     * @param model
     */
    protected void updateModel(final String name, final Model model) {
        model.addAttribute("name", name);
        model.addAttribute("collection", getEngineDB().findAllItems().toArray(new DBItem1[0]));
    }

    /**
     *
     */
    protected static Logger log = LoggerFactory.getLogger(ControllerMain.class);

}
