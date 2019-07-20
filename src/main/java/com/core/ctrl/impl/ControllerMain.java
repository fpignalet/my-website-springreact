package com.core.ctrl.impl;

import com.core.async.AAsyncTasks;
import com.core.ctrl.AControllerBase;
import com.core.ctrl.EHTMLPages;
import com.core.eng.impl.EngServiceDBABook;
import com.core.eng.impl.EngServiceDBCV;
import com.core.eng.impl.EngServiceDBTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        try {
            return EHTMLPages.DEFAULT.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/entrydefault")
    public String entrydefault(final Model model){
        try {
            return EHTMLPages.DEFAULT.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * to be tested in browser
     */
    @PostMapping(value = "/activatetask")
    public String activatetask(@RequestParam(value="task", required = false, defaultValue = "") final String names) {
        try {
            taskManager.activate(names);
            return EHTMLPages.DEFAULT.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * to be tested in browser
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryaddressbook")
    public String entryaddressbook(final Model model){
        try {
            getEngineContact().updateModel(model);
            return EHTMLPages.ADBOOK.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * to be tested in browser
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entrycv")
    public String entryCV(final Model model){
        try {
            getEngineCV().updateModel(model);
            return EHTMLPages.CV.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * to be tested in browser
     * will display react html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryreact")
    public String entryreact(final Model model){
        try {
            return EHTMLPages.REACT.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /**
     * to be tested in browser
     * will display angular html page with "FROM MODEL:[name parameter content]"
     * @param model
     * @return
     */
    @GetMapping("/entryangular")
    public String entryangular(final Model model){
        try {
            return EHTMLPages.ANGULAR.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return EHTMLPages.ERROR.getName();
        }
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param engineDB
     * @param engineCV
     * @param engineContact
     * @param taskManager
     */
    public ControllerMain(
        final EngServiceDBTest engineDB,
        final EngServiceDBCV engineCV,
        final EngServiceDBABook engineContact,
        final AAsyncTasks taskManager) {
        super(engineDB, engineCV, engineContact, null);
        this.taskManager = taskManager;
    }

    @Autowired
    final AAsyncTasks taskManager;

}
