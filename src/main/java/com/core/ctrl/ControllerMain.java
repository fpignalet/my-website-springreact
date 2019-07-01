package com.core.ctrl;

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
        "entrymain",
        "entryreact",
        "index",
        "CV",
        "BLOGNEWS",
        "BLOGTECH",
        "OFFER",
        "impressum"
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
    @GetMapping("/entrymain")
    public String entrytest(
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
            return pageNames[0];
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
        model.addAttribute("name", name + ", WITH BEAN TEST: " + getEngineJSON().doReactTest());
        return pageNames[1];
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
        return pageNames[2];
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/CV")
    public String CV(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        return pageNames[3];
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/BLOGNEWS")
    public String BLOGNEWS(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        return pageNames[4];
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/BLOGTECH")
    public String BLOGTECH(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        return pageNames[5];
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/OFFER")
    public String OFFER(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        return pageNames[6];
    }

    /**
     * @brief root entry function
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/impressum")
    public String impressum(
        @RequestParam(name="name", required=false, defaultValue="MAIN CONTROLER") String name,
        Model model)
    {
        return pageNames[7];
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
            return new AsyncResult<>("entryreact");
        }
        catch (InterruptedException e) {
            log.error(e.toString());
            return new AsyncResult<>("entrytest");
        }
    }

}
