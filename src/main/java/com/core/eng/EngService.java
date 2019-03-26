package com.core.eng;

import com.core.data.DBItem1DAO;
import com.core.data.DBItem1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 *
 */
@Service
@ComponentScan({"com.core.data"})
public class EngService {

    /*************************************************************************
     MULTI-PURPOSE
     *************************************************************************/
    /**
     * @return
     */
    public String doReactTest() {
        return "TEST ENGINE1";
    }

    /*************************************************************************
     DATA TESTS
     *************************************************************************/
    /**
     * @param fileName
     * @return
     */
    public String doLoadJSON(final String fileName) {
        try {
            final Path path = Paths.get(fileName);
            final Charset charset = StandardCharsets.UTF_8;

            final String jsonstr =
                    new String(Files.readAllBytes(path))
                    .substring("module.exports = ".length())
                    .replace(";", "");

            return jsonstr;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * @param param
     * @return
     */
    public String getAnswerJSON(final String param) {
        try {
            final HashMap<String, String> result = new HashMap<String, String>();
            result.put("item1", param);

            final String jsonstr = new ObjectMapper().writeValueAsString(result);

            return jsonstr;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /*************************************************************************
     MAIL OPERATION
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

    /*************************************************************************
     DATABASE ACCESS
     *************************************************************************/
    @Autowired
    private DBItem1DAO data1Repo;

    /**
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String addOneItem1(final int id, final String name) {
        final DBItem1 item = new DBItem1();
        item.setId(id); item.setName(name);
        data1Repo.save(item);
        log.info("OK");
        return "NEW ITEM 1 SAVED";
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String updateOneItem1(final int id, final String name) {
        final DBItem1 item = data1Repo.findById(id).get();
        item.setName(name);
        data1Repo.save(item);
        log.info("OK");
        return "NEW ITEM 1 UPDATED";
    }

    /**
     * @return
     */
    public List<DBItem1> findAllOrderedByNameDescending(){
        List<DBItem1> items = (List<DBItem1>) data1Repo.findAllOrderedByNameDescending();
        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public String getAllItems() {
        final StringBuilder result = new StringBuilder();

        data1Repo.findAll().forEach(
            (it)->{ result.append(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
        );

        log.info("OK");
        return result.toString();
    }

    /*************************************************************************
     INIT PART
     *************************************************************************/
    /**
     * @param emailSender
     * @param data1Repo
     */
    public EngService(final JavaMailSender emailSender, final DBItem1DAO data1Repo) {
        this.emailSender = emailSender;
        this.data1Repo = data1Repo;
        log.info("OK");
    }

    /**
     *
     */
    private static Logger log = LoggerFactory.getLogger(EngService.class);

}
