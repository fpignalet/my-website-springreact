package com.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 */
@Service
public class CoreEngine1 {

    /**
     * @return
     */
    public String execute_reactTest() {
        return "TEST ENGINE1";
    }

    /**
     * @param param
     * @return
     */
    public String execute_answerJSON(final String param) {
        try {
            final HashMap<String, String> result = new HashMap<String, String>();
            result.put("item1", param);
            final String jsonstr = new ObjectMapper().writeValueAsString(result);
            return jsonstr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * @param fileName
     * @return
     */
    public String execute_loadJSON(final String fileName) {
        try {
            final Path path = Paths.get(fileName);
            final Charset charset = StandardCharsets.UTF_8;
            final String content = new String(Files.readAllBytes(path));
            String filtered = content
                    .replace("module.exports = ", "")
                    .replace("};", "");
            final String jsonstr = new ObjectMapper().writeValueAsString(filtered);
            return jsonstr;
        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     *
     */
    @Autowired
    public final JavaMailSender emailSender;

    /**
     * @param emailSender
     */
    public CoreEngine1(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * @param to
     * @param subject
     * @param text
     */
    public void sendSimpleMessage(String to, String subject, String text) {

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * @return
     */
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("francois.pignalet@gmail.com");
        mailSender.setPassword("password");

        final Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
