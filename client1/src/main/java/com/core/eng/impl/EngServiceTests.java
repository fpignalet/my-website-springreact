package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Mail operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
public class EngServiceTests implements IEngModelUpdater {

    /************************************************************************
     INTERFACE ENFORCING
     */

    /**
     * @brief
     */
    @Override
    public void updateModel(Model model) throws IOException {
        log.debug("Not yet implemented");
    }

    /************************************************************************
     IMPLEM PART

     /**
     * @param request
     * @return
     */
    public String testActuator(final HttpServletRequest request) {
        final StringBuilder sb = new StringBuilder();

        // Spring Boot >= 2.0.0.M7
        final String endpointBasePath = "/actuator";
        sb.append("<h2>Spring Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        final String contextPath = request.getContextPath();
        final String host = request.getServerName();
        final String url = "http://" + host + ":8090" + contextPath + endpointBasePath;
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");

        sb.append("</ul>");

        return sb.toString();
    }

    /************************************************************************
     INIT PART
     */

    private static final String RESOURCE_URL = "/client2test/sub/12";

}
