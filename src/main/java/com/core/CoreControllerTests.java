package com.core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class CoreControllerTests extends ACoreControllerBase{

    /**
     * @param engine1
     */
    public CoreControllerTests(EngService engine1) {
        super(engine1);
    }

    /**
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/reacttest")
    public String reacttest(
        @RequestParam(name="name", required=false, defaultValue="RETEST") String name,
        Model model)
    {
        model.addAttribute("name", name + ", WITH BEAN TEST: " + getEngine1().doReactTest());
        log.info("OK");
        return "reacttest";
    }

    /**
     * @param param
     * @return
     */
    @GetMapping("/httptest1")
    @ResponseBody
    public String httptest1(
        @RequestParam(name="param", required=true, defaultValue="DEFAULT") String param)
    {
        log.info("OK");
        return getEngine1().getAnswerJSON(param);
    }

    /**
     * @return
     */
    @GetMapping("/httptest2")
    @ResponseBody
    public String httptest2() {
        log.info("OK");
        return getEngine1().doLoadJSON("src/main/resources/static/testdata.json");
    }

    /**
     * @return
     */
    @GetMapping("/httptest3")
    @ResponseBody
    public String httptest3() {
        log.info("OK");
        return getEngine1().doLoadJSON("src/main/resources/static/njcv.json");
    }
}
