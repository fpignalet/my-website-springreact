package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Mail operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
public class EngServiceTests extends AEngJSONHandler implements IEngModelUpdater {

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
     * @param request
     * @return
     */
    public String testActuator(final HttpServletRequest request) {
        final String contextPath = request.getContextPath();
        final String host = request.getServerName();

        // Spring Boot >= 2.0.0.M7
        final String endpointBasePath = "/actuator";
        final StringBuilder sb = new StringBuilder();
        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        final String url = "http://" + host + ":8090" + contextPath + endpointBasePath;
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");
        sb.append("</ul>");

        return sb.toString();
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public String testMicroserviceComREST(final HttpServletRequest request) throws IOException {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL, String.class);
        return parse(response);
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public String testMicroserviceComMQ(final HttpServletRequest request) throws IOException {
        return "TEMP";
    }

    /************************************************************************
     FILE ACCESS PART
     */

    @Override
    public <T> ArrayList<?> loadFile() throws IOException {
        return null;
    }

    @Override
    public void updateFile(ArrayList<?> itemsDB) throws IOException {
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param emailSender receives autowired JavaMailSender
     */
    public EngServiceTests(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
        log.debug("OK");
    }

    /**
     *
     */
    @Autowired
    private final JavaMailSender emailSender;

    private static final String RESOURCE_URL = "http://localhost:8081/client2test/sub/12";

}
