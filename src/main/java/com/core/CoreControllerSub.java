package com.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoreControllerSub {

    @Autowired
    private final CoreEngine1 engine1;

    public CoreControllerSub(CoreEngine1 engine1) {
        this.engine1 = engine1;
    }

    @GetMapping("/reacttest")
    public String retest(@RequestParam(name="name", required=false, defaultValue="RETEST") String name, Model model) {
        model.addAttribute("name", name + ", WITH BEAN TEST: " + this.engine1.test());
        return "reacttest";
    }

}
