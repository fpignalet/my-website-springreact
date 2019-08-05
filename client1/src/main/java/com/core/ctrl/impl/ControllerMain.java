package com.core.ctrl.impl;

import com.core.async.AAsyncTasks;
import com.core.ctrl.AControllerBase;
import com.core.ctrl.EHTMLPages;
import com.core.eng.impl.EngServiceDBABook;
import com.core.eng.impl.EngServiceDBHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Display main html pages available (see EHtmlPages)
 */
@Slf4j
@Controller
public class ControllerMain extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART
     */

    /**
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(final Model model){
        return EHTMLPages.DEFAULT.getName();
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/entrydefault")
    public String entrydefault(final Model model){
        return EHTMLPages.DEFAULT.getName();
    }

    /**
     * to be tested in browser
     */
    @PostMapping(value = "/activatetask")
    public String activatetask(@RequestParam(value="task", required = false, defaultValue = "") final String names) {
        taskManager.activate(names);
        return EHTMLPages.DEFAULT.getName();
    }

    /**
     * will display address bool html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryaddressbook")
    public String entryaddressbook(final Model model) throws IOException {
        engineHistory.updateModel(model);
        return EHTMLPages.ADBOOK.getName();
    }

    /**
     * will display CV html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entrycv")
    public String entryCV(final Model model) throws IOException {
        engineHistory.updateModel(model);
        return EHTMLPages.CV.getName();
    }

    /**
     * will display blog html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryblog")
    public String entryBLOG(final Model model) throws IOException {
        engineHistory.updateModel(model);
        return EHTMLPages.BLOG.getName();
    }

    /**
     * will display react html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryreact")
    public String entryreact(final Model model){
        return EHTMLPages.REACT.getName();
    }

    /**
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryangular")
    public String entryangular(final Model model){
        return EHTMLPages.ANGULAR.getName();
    }

    /**
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryredis")
    public String entryredis(final Model model){
        return EHTMLPages.REDIS.getName();
    }

    /**
     * will display tasks html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entrytasks")
    public String entrytasks(final Model model){
        return EHTMLPages.TASKS.getName();
    }

    /************************************************************************
     INIT PART
     */

    @Autowired
    private EngServiceDBABook engineContact;

    @Autowired
    private EngServiceDBHistory engineHistory;

    @Autowired
    private AAsyncTasks taskManager;

}
