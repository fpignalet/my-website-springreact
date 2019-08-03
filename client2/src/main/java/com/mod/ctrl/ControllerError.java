package com.mod.ctrl;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Display error page
 */
@RestController
public class ControllerError implements ErrorController {

    /**
     * @return
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, HttpServletResponse response) {
        return "error: " + request.getQueryString() + " / " + response.getContentType();
    }

    /**
     *
     */
    private static final String PATH = "/error";

}