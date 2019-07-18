package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Properties;

/**
 * Mail operations
 */
@Service
@Slf4j
@ComponentScan({"com.core.data"})
public class EngServiceMail implements IEngModelUpdater {

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

    /*************************************************************************
     MAIL HANDLING
     *************************************************************************/
    /**
     * @brief Send a mail
     * @param to contains the destination address
     * @param subject contains the title of the mail
     * @param text contains the text to be sent
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

        log.debug("OK");
    }

    /**
     * @brief
     * @return a JavaMailSenderImpl
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

        log.debug("OK");
        return mailSender;
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param emailSender receives autowired JavaMailSender
     */
    public EngServiceMail(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
        log.debug("OK");
    }

    /**
     *
     */
    @Autowired
    private final JavaMailSender emailSender;

}
