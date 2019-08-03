package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
        final StringBuilder sb = new StringBuilder();

        // Spring Boot >= 2.0.0.M7
        final String endpointBasePath = "/actuator";
        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        final String contextPath = request.getContextPath();
        final String host = request.getServerName();
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
        final Application application = eurekaClient.getApplication("CLIENT2");
        final InstanceInfo instanceInfo = application.getInstances().get(0);
        final String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        final String url = "http://" + hostname + ":" + port + RESOURCE_URL;
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return parse(response.getBody());
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
     * @param restTemplate
     * @param eurekaClient
     */
    public EngServiceTests(RestTemplate restTemplate, EurekaClient eurekaClient) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
        log.debug("OK");
    }

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final EurekaClient eurekaClient;

    private static final String RESOURCE_URL = "/client2test/sub/12";

}
