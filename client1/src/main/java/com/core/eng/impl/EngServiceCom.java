package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import com.core.msg.MsgEmail;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Mail operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
public class EngServiceCom extends AEngJSONHandler implements IEngModelUpdater {

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
     */

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public String testMicroserviceComREST(final HttpServletRequest request) throws IOException {
        final Application application = eurekaClient.getApplication(RESOURCE_NAME);
        final InstanceInfo instanceInfo = application.getInstances().get(0);
        final String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + RESOURCE_URL;
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return parse(response.getBody());
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public String testMicroserviceComMQ(final HttpServletRequest request) throws IOException {
        return "STUBBED";
    }

    /**
     * @return
     * @throws Exception
     */
    public String testSimpleHttpRequest() throws Exception {
        final URL google = new URL(HTTP_WWW_GOOGLE_COM);
        final URLConnection yc = google.openConnection();
        final BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        return inputLine;
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public String testJMSMail(final HttpServletRequest request) throws IOException {
        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an email message.");
        msgTemplate.convertAndSend("mailbox", new MsgEmail("info@example.com", "Hello"));
        return "JMS OK";
    }

    /************************************************************************
     INTERFACE ENFORCING
     FILE ACCESS PART
     */

    @Override
    public <T> ArrayList<?> loadFile() throws IOException {
        return null;
    }

    @Override
    public void updateFile(final ArrayList<?> itemsDB) throws IOException {
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @param restTemplate
     * @param eurekaClient
     * @param msgTemplate
     */
    public EngServiceCom(RestTemplate restTemplate, EurekaClient eurekaClient, JmsTemplate msgTemplate) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
        this.msgTemplate = msgTemplate;
        log.debug("OK");
    }

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final EurekaClient eurekaClient;

    @Autowired
    private final JmsTemplate msgTemplate;

    private static final String RESOURCE_NAME = "CLIENT2";
    private static final String RESOURCE_URL = "/client2test/sub/12";

    public static final String HTTP_WWW_GOOGLE_COM = "http://www.google.com/";
    
}
