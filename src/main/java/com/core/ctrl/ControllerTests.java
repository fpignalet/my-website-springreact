package com.core.ctrl;

import com.core.eng.EngServiceJSON;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerTests extends AControllerBase {

    /**
     * @param engineJSON
     */
    public ControllerTests(EngServiceJSON engineJSON) {
        super(null, engineJSON, null);
    }

    /**
     * to be tested in browser
     * when url is for example "http://localhost:8080/httpreturnparam?param=TOTO"
     * display will be "{"item1":"TOTO"}"
     * @param param contains the value of the request parameter
     * @return
     */
    @GetMapping("/httpreturnparam")
    @ResponseBody
    public String httpreturnparam(
        @RequestParam(name="param", required=true, defaultValue="DEFAULT") String param)
    {
        return getEngineJSON().createAnswerJSON(param);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.js data file
     */
    @GetMapping("/httpgetdatatest")
    @ResponseBody
    public String httpgetdatatest() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @GetMapping("/httpgetdatafpi")
    @ResponseBody
    public String httpgetdatafpi() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
    }

    /**
     * to be tested in browser
     * will display react html page with "FROM MODEL:[name parameter content]"
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/reacttest")
    public String reacttest(
            @RequestParam(name="name", required=false, defaultValue="RETEST") String name,
            Model model)
    {
        model.addAttribute("name", name + ", WITH BEAN TEST: " + getEngineJSON().doReactTest());
        return "reacttest";
    }

    /**
     * to be tested in browser
     * not yet working...
     * @throws InterruptedException
     */
    @GetMapping("/asynctest1")
    @Async
    public Future<String> methodAsync1() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("reacttest");
        }
        catch (InterruptedException e) {
            log.error(e.toString());
            return new AsyncResult<>("entrytest");
        }
    }

}
