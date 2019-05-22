package com.core.ctrl;

import com.core.data.DBHistContener;
import com.core.data.DBHistContent;
import com.core.data.DBHistItem;
import com.core.data.DBHistSub;
import com.core.eng.EngServiceDB;
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

import java.io.IOException;
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
    public ControllerTests(EngServiceJSON engineJSON, EngServiceDB engineDB) {
        super(engineDB, engineJSON, null);
    }

    /**
     * to be tested in browser
     * when url is for example "http://localhost:8080/testhttpgetfromparam?param=TOTO"
     * display will be "{"item1":"TOTO"}"
     * @param param contains the value of the request parameter
     * @return
     */
    @GetMapping("/testhttpgetfromparam")
    @ResponseBody
    public String testhttpgetfromparam(
        @RequestParam(name="param", defaultValue="DEFAULT") String param)
    {
        return getEngineJSON().createAnswerJSON(param);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.js data file
     */
    @GetMapping("/testhttpgetjson0")
    @ResponseBody
    public String testhttpgetjson0() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @GetMapping("/testhttpgetjson1")
    @ResponseBody
    public String testhttpgetjson1() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatedb")
    @ResponseBody
    public void testpopulatedb() {
        try {
            final EngServiceJSON instanceJS = getEngineJSON();
            final String data = instanceJS.doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
            final EngServiceJSON.DBConteners conteners = (EngServiceJSON.DBConteners) instanceJS.parse(data,
                EngServiceJSON.DBConteners.class,
                new Class[]{
                    DBHistContener.class,
                    DBHistItem.class,
                    DBHistContent.class,
                    DBHistSub.class
                }
            );
            final EngServiceDB instanceDb = getEngineDB();
            instanceDb.populateDB(conteners);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * to be tested in browser
     * will display react html page with "FROM MODEL:[name parameter content]"
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/testreact")
    public String testreact(
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
    @GetMapping("/testasync")
    @Async
    public Future<String> methodAsync1() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("testreact");
        }
        catch (InterruptedException e) {
            log.error(e.toString());
            return new AsyncResult<>("entrytest");
        }
    }

}
