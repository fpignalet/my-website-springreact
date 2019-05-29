package com.core.ctrl;

import com.core.data.impl.DBHistContener;
import com.core.data.impl.DBHistContent;
import com.core.data.impl.DBHistItem;
import com.core.data.impl.DBHistSub;
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
    public String exthttpgetjson0() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
    @CrossOrigin
    public String exthttpgetjson1() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datatest.js data file
     */
    @GetMapping("/testhttpgetjson0")
    public String testhttpgetjson0() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
    }

    /**
     * to be tested in browser
     * @return the content of src/main/resources/static/datafpi.js data file
     */
    @GetMapping("/testhttpgetjson1")
    public String testhttpgetjson1() {
        return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
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
        @RequestParam(name="param", defaultValue="DEFAULT") String param)
    {
        return getEngineJSON().createAnswerJSON(param);
    }

    /**
     * to be tested in browser
     */
    @GetMapping("/testpopulatedb")
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

            final List<DBHistContener> itemsDB = instanceDb.findAllItemsHist();
            instanceJS.updateFile(itemsDB);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
