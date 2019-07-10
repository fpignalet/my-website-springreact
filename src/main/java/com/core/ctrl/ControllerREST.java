package com.core.ctrl;

import com.core.data.impl.DBConteners;
import com.core.data.impl.DBHistContener;
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
     * @param engineDB
     * @param engineJSON
     */
    public ControllerREST(final EngServiceDB engineDB, final EngServiceJSON engineJSON) {
        super(engineDB, engineJSON, null);
    }

    /**
     * @param param
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/guirequestfrommain")
    @CrossOrigin
    public String requestfrommain(
        @RequestParam(name="param", required=false, defaultValue="guirequestfrommain ANSWER")
        String param
    ) throws InterruptedException {
        return "RESPONSE FOR MAIN: " + param;
    }

    /**
     * @param param
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/guirequestfromreact")
    @CrossOrigin
    public String requestfromreact(
        @RequestParam(name="param", required=false, defaultValue="guirequestfromreact ANSWER")
        String param
    ) throws InterruptedException {
        return "RESPONSE FOR REACT: " + param;
    }

    /**
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson0() throws IOException {
        return getEngineJSON().load(0);
    }

    /**
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson1() throws IOException {
        return getEngineJSON().load(1);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.json data file
     * @throws IOException
     */
    @GetMapping("/testhttpgetjson0")
    @CrossOrigin
    public String testhttpgetjson0() throws IOException {
        return getEngineJSON().load(0);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     * @throws IOException
     */
    @GetMapping("/testhttpgetjson1")
    @CrossOrigin
    public String testhttpgetjson1() throws IOException {
        return getEngineJSON().load(1);
    }

    /**
     * to be tested in browser
     * when url is for example "http://localhost:8080/testhttpgetfromparam?param=TOTO"
     * display will be "{"item1":"TOTO"}"
     * @param param contains the value of the request parameter
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/testhttpgetfromparam")
    @CrossOrigin
    public String testhttpgetfromparam(
        @RequestParam(name="param", defaultValue="DEFAULT")
        String param
    ) throws JsonProcessingException {
        return getEngineJSON().createAnswerJSON(param);
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatedb")
    @CrossOrigin
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
    @CrossOrigin
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
