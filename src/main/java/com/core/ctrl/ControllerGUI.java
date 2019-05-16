package com.core.ctrl;

import com.core.eng.EngServiceDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * using thymeleaf fragments
 */
@RestController
@Slf4j
@ComponentScan({"com.core.eng", "com.core.data"})
public class ControllerGUI extends AControllerBase {

    /**
     */
    public ControllerGUI(final EngServiceDB engineDB) {
        super(engineDB, null, null);
    }

    /**
     * @return
     */
    @GetMapping("/guirequestfrommain")
    public String requestfrommain(
            @RequestParam(name="param", required=false, defaultValue="DEFAULT ANSWER") String param
    ) throws InterruptedException {
        Thread.currentThread().sleep(10000);
        return "RESPONSE FOR MAIN: " + param;
    }

    /**
     * @return
     */
    @GetMapping("/guirequestfromreact")
    public String requestfromreact(
            @RequestParam(name="param", required=false, defaultValue="DEFAULT ANSWER") String param
    ) throws InterruptedException {
        Thread.currentThread().sleep(10000);
        return "RESPONSE FOR REACT: " + param;
    }

}
