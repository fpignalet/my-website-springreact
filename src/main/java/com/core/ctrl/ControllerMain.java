package com.core.ctrl;

import com.core.eng.EngService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main Controller. Keep it most simple as possible
 */
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerMain extends AControllerBase {

    /**
     * @brief controller
     * @param engine1
     */
    public ControllerMain(EngService engine1) {
        super(engine1);
    }

    /**
     * @brief root entry function
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
        return "entrymain";
    }

}
