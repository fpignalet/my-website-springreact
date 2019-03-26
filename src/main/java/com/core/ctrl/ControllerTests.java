package com.core.ctrl;

import com.core.eng.EngService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

/**
 *
 */
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerTests extends AControllerBase {

    /**
     * @param engine1
     */
    public ControllerTests(EngService engine1) {
        super(engine1);
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
        return getEngine1().doLoadJSON("src/main/resources/static/datatest.js");
    }

    /**
     * @return
     */
    @GetMapping("/httptest3")
    @ResponseBody
    public String httptest3() {
        log.info("OK");
        return getEngine1().doLoadJSON("src/main/resources/static/datafpicv.js");
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
     * @throws InterruptedException
     */
    @GetMapping("/asynctest1")
    @Async("asyncExecutor")
    public Future<String> methodAsync1() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("reacttest");
        }
        catch (InterruptedException e) {

        }

        return new AsyncResult<>("entrytest");
    }

}
