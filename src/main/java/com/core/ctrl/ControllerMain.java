package com.core.ctrl;

import com.core.ext.ExtFacade;
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

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * using thymeleaf fragments
 */
@Slf4j
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerMain extends AControllerBase {

    /**
     * in templates:
     */
    private final static String[] pageNames = {
        "frontend_default",
        "frontend_react",
        "frontend_angular",
        "frontend_error"
    };

    /**
     */
    public ControllerMain(final EngServiceDB engineDB, final EngServiceJSON engineJSON) {
        super(engineDB, engineJSON, null);
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        try {
            model.addAttribute("name", name);
            getEngineDB().updateModel(model);
            getEngineJSON().updateModel(model);
            return pageNames[0];
        } catch (IOException e) {
            e.printStackTrace();
            return pageNames[3];
        }
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/entrydefault")
    public String entrydefault(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        try {
            model.addAttribute("name", name);
            getEngineDB().updateModel(model);
            getEngineJSON().updateModel(model);
            return pageNames[0];
        } catch (IOException e) {
            e.printStackTrace();
            return pageNames[3];
        }
    }

    /**
     * to be tested in browser
     * will display react html page with "FROM MODEL:[name parameter content]"
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/entryreact")
    public String entryreact(
        @RequestParam(name="name", required=false, defaultValue="RETEST") String name,
        Model model)
    {
        try {
            model.addAttribute("name", name + ", WITH BEAN TEST: " + getEngineJSON().doReactTest());
            return pageNames[1];
        } catch (Exception e) {
            e.printStackTrace();
            return pageNames[3];
        }
    }

    /**
     * to be tested in browser
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/entryangular")
    public String entryangular(
        @RequestParam(name="name", required=false, defaultValue="RETEST") String name,
        Model model)
    {
        try {
            model.addAttribute("name", name + ", WITH BEAN TEST: " + getEngineJSON().doReactTest());
            return pageNames[2];
        } catch (Exception e) {
            e.printStackTrace();
            return pageNames[3];
        }
    }

    /**
     * to be tested in browser
     * not yet working...
     * @throws InterruptedException
     */
    @GetMapping("/entryasync")
    @Async
    public Future<String> methodAsync1() {
        try {
            log.info("Execute method asynchronously - " + Thread.currentThread().getName());

            Thread.sleep(5000);
            return new AsyncResult<>(pageNames[1]);
        }
        catch (InterruptedException e) {
            log.error(e.toString());
            return new AsyncResult<>(pageNames[3]);
        }
    }

}
