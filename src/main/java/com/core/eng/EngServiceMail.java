package com.core.eng;

import com.core.data.DBItemTestDAO;
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
    @Override
    public void updateModel(Model model) throws IOException {
        log.debug("Not yet implemented");
    }

    /*************************************************************************
     MAIL HANDLING
     *************************************************************************/
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
        emailSender.send(message);

        log.debug("OK");
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

        log.debug("OK");
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
        log.debug("OK");
    }

}
