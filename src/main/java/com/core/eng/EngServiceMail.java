package com.core.eng;

import com.core.data.DBItemTestDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 *
 */
@Service
@Slf4j
@ComponentScan({"com.core.data"})
public class EngServiceMail {

    /************************************************************************
     MAIL OPERATION
     */
    /**
     *
     */
    @Autowired
    private final JavaMailSender emailSender;

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

        log.info("OK");
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

        log.info("OK");
        return mailSender;
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @param emailSender
     * @param data1Repo
     */
    public EngServiceMail(final JavaMailSender emailSender, final DBItemTestDAO data1Repo) {
        this.emailSender = emailSender;
        log.info("OK");
    }

}
