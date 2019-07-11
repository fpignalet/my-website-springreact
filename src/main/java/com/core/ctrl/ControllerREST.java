package com.core.ctrl;

import com.core.data.impl.DBConteners;
import com.core.data.impl.DBHistContener;
import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
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
     */
    @GetMapping("/guirequestfrommain")
    @CrossOrigin
    public String requestfrommain(
        @RequestParam(name="param", required=false, defaultValue="guirequestfrommain ANSWER")
        String param
    ) {
        return "RESPONSE FOR MAIN: " + param;
    }

    /**
     * @param param
     * @return
     */
    @GetMapping("/guirequestfromreact")
    @CrossOrigin
    public String requestfromreact(
        @RequestParam(name="param", required=false, defaultValue="guirequestfromreact ANSWER")
        String param
    ) {
        return "RESPONSE FOR REACT: " + param;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson0() {
        try {
            return getEngineJSON().load(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson1() {
        try {
            return getEngineJSON().load(1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.json data file
     */
    @GetMapping("/testhttpgetjson0")
    @CrossOrigin
    public String testhttpgetjson0() {
        try {
            return getEngineJSON().load(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @GetMapping("/testhttpgetjson1")
    @CrossOrigin
    public String testhttpgetjson1() {
        try {
            return getEngineJSON().load(1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * to be tested in browser
     * when url is for example "http://localhost:8080/testhttpgetfromparam?param=TOTO"
     * display will be "{"item1":"TOTO"}"
     * @param param contains the value of the request parameter
     * @return
     */
    @GetMapping("/testhttpgetfromparam")
    @CrossOrigin
    public String testhttpgetfromparam(
        @RequestParam(name="param", defaultValue="DEFAULT")
        String param
    ) {
        try {
            return getEngineJSON().doJSONTest(param);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatedb")
    @CrossOrigin
    public String testpopulatedb() {
        try {
            final EngServiceJSON instanceJs = getEngineJSON();
            final DBConteners conteners = instanceJs.load();

            final EngServiceDB instanceDb = getEngineDB();
            instanceDb.update(conteners);

            return "DB FILLED";
        } catch (IOException e) {
            e.printStackTrace();
            return "DB NOT FILLED";
        }
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatefile")
    @CrossOrigin
    public String testpopulatefile() {
        try {
            final EngServiceDB instanceDb = getEngineDB();
            final List<DBHistContener> itemsDB = instanceDb.findAllItemsHist();

            final EngServiceJSON instanceJS = getEngineJSON();
            instanceJS.update(itemsDB);

            return "FILE FILLED";
        } catch (IOException e) {
            e.printStackTrace();
            return "FILE NOT FILLED";
        }
    }

}
