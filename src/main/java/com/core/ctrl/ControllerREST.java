package com.core.ctrl;

import com.core.data.impl.DBConteners;
import com.core.data.impl.DBHistContener;
import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import com.core.ext.ExtFacade;
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
     */
    @GetMapping("/extpopulatedb")
    @CrossOrigin
    public String extpopulatedb() {
        try {
            final EngServiceJSON instanceJs = getEngineJSON();
            final DBConteners conteners = instanceJs.load();

            final EngServiceDB instanceDb = getEngineDB();
            instanceDb.update(conteners);

            return "{ \"result\": \"DB FILLED\" }";
        } catch (IOException e) {
            e.printStackTrace();
            return "{ \"result\": \"DB NOT FILLED\" }";
        }
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/extpopulatefile")
    @CrossOrigin
    public String extpopulatefile() {
        try {
            final EngServiceDB instanceDb = getEngineDB();
            final List<DBHistContener> itemsDB = instanceDb.findAllItemsHist();

            final EngServiceJSON instanceJS = getEngineJSON();
            instanceJS.update(itemsDB);

            return "{ \"result\": \"FILE FILLED\" }";
        } catch (IOException e) {
            e.printStackTrace();
            return "{ \"result\": \"FILE NOT FILLED\" }";
        }
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/extnativelib")
    @CrossOrigin
    public String extnativelib() {
        try {
            final String result_execute = extFacade.test_execute();
            log.info(result_execute);

            final String[] result_getData = extFacade.test_getData();
            for(final String data: result_getData){
                log.info(data);
            }

            return "{ \"result\": \"LIBRARY OOK\" }";
        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"result\": \"LIBRARY NOT OOK\" }";
        }
    }

    /**
     *
     */
    private ExtFacade extFacade = new ExtFacade();

}
