package com.core.ctrl;

import com.core.data.impl.*;
import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * using thymeleaf fragments
 */
@Slf4j
@RestController
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerREST extends AControllerBase {

    /**
     */
    public ControllerREST(final EngServiceDB engineDB, final EngServiceJSON engineJSON) {
        super(engineDB, engineJSON, null);
    }

    /**
     * @return
     */
    @GetMapping("/guirequestfrommain")
    public String requestfrommain(
            @RequestParam(name="param", required=false, defaultValue="DEFAULT ANSWER") String param
    ) throws InterruptedException {
        Thread.sleep(10000);
        return "RESPONSE FOR MAIN: " + param;
    }

    /**
     * @return
     */
    @GetMapping("/guirequestfromreact")
    public String requestfromreact(
            @RequestParam(name="param", required=false, defaultValue="DEFAULT ANSWER") String param
    ) throws InterruptedException {
        Thread.sleep(10000);
        return "RESPONSE FOR REACT: " + param;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson0() throws IOException {
        return getEngineJSON().load(0);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson1() throws IOException {
        return getEngineJSON().load(1);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.js data file
     */
    @GetMapping("/testhttpgetjson0")
    public String testhttpgetjson0() throws IOException {
        return getEngineJSON().load(0);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @GetMapping("/testhttpgetjson1")
    public String testhttpgetjson1() throws IOException {
        return getEngineJSON().load(1);
    }

    /**
     * to be tested in browser
     * when url is for example "http://localhost:8080/testhttpgetfromparam?param=TOTO"
     * display will be "{"item1":"TOTO"}"
     * @param param contains the value of the request parameter
     * @return
     */
    @GetMapping("/testhttpgetfromparam")
    public String testhttpgetfromparam(
        @RequestParam(name="param", defaultValue="DEFAULT") String param) throws JsonProcessingException {
        return getEngineJSON().createAnswerJSON(param);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/serverside", method = RequestMethod.GET)
    @CrossOrigin
    public String serverside(
        @RequestParam(name="p1", required=false, defaultValue="DEFAULT") String p1,
        @RequestParam(name="p2", required=false, defaultValue="DEFAULT") String p2,
        @RequestParam(name="p3", required=false, defaultValue="DEFAULT") String p3,
        @RequestParam(name="p4", required=false, defaultValue="DEFAULT") String p4
    ) throws IOException {
        final EngServiceJSON instanceJs = getEngineJSON();
        final DBConteners conteners = instanceJs.load();

        log.info("received request /serverside p1={} p2={} p3={} p4={}", p1, p2, p3, p4);

        final EngServiceDB instanceDb = getEngineDB();
        final List<DBHistContener> itemsDB = instanceDb.filter(conteners, new String[]{ p1, p2, p3, p4 });

//        final String result = getEngineJSON().fill(itemsDB);
        final String result = itemsDB.get(0).toJSONString();
        return result;
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatedb")
    public void testpopulatedb() {
        try {
            final EngServiceJSON instanceJs = getEngineJSON();
            final DBConteners conteners = instanceJs.load();

            final EngServiceDB instanceDb = getEngineDB();
            instanceDb.update(conteners);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatefile")
    public void testpopulatefile() {
        try {
            final EngServiceDB instanceDb = getEngineDB();
            final List<DBHistContener> itemsDB = instanceDb.findAllItemsHist();

            final EngServiceJSON instanceJS = getEngineJSON();
            instanceJS.update(itemsDB);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
