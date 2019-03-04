package com.core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoreControllerMain {

    @GetMapping("/")
    public String hello(@RequestParam(name="name", required=false, defaultValue="TOTO 1") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
