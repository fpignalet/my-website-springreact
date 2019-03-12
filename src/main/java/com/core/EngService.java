package com.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
import java.util.concurrent.Executor;

/**
 * NOTES
 *
 * CRUD REPO ******************************************************************************************************************************
 Spring Boot Data enables JPA repository support by default.
 CrudRepository provides generic CRUD operation on a repository for a specific type.
 CrudRepository is a Spring data interface and to use it we need to create our interface by extending CrudRepository.
 Spring provides CrudRepository implementation class automatically at runtime.
 It contains methods such as save, findById, delete, count etc.
 Spring boot automatically detects our repository if the package of that repository interface is the same or sub-package of the class annotated with @SpringBootApplication.
 *
 * J2EE / SPRING ******************************************************************************************************************************
 - What is Java Standard Edition (This will clear the confusion related to JDK, J2SE, JavaSE)
 In general, you can say this is the core of Java.(The prime part)
 You need it, for developing desktop applications as well as web based applications.
 It provides everything from basic objects to high level classes which are used for networking, database access, security, XML parsing, GUI development.
 Along with this core APIs, it also provides virtual machine (JVM), development tools, deployment technologies etc. (Check here for more details - JDK Development Tools)
 Below diagram will show you the version history of Java Standard Edition. - image taken from http://codingfox.com
 You can see that Java Standard Edition was called by different names in different years. Hence these different names came into existent, which is now creating the confusion to beginners ;)
 But JDK, J2SE, JavaSE are same core part of Java, with more enhanced features and more classes and functionalities.
 - What is Java Enterprise Edition (This will clear confusion about J2EE, JavaEE)
 Java Enterprise Edition is an abstract specification.
 The concrete implementation are so called as the application servers like - GlassFish, WildFly, WebLogic.
 When you download JavaEE from Oracle's site, it will give you GlassFish server with bunch of documentations and examples. So they are just providing the implementation of Java Enterprise Edition specification.
 You can also prefer to use the other implementations like RedHat WildFly which also follows these specifications.
 Below is the version history- taken from Imgur
 So, J2EE, JavaEE are just the different versions.
 Does EJB follows JavaEE specifications?
 Yes, EJB is a part of JavaEE specifications. Full fledge JavaEE application server support EJB's out of the box.
 This means you can NOT run EJB applications on simple servlet container like Tomcat.
 Does Spring follows "ALL" JavaEE specifications?
 Strictly speaking NO
 Spring is a standalone framework, which has substituted and improves many parts of JavaEE.
 You can consider the Spring as an integration platform that lets you use all JavaEE technologies.
 That means you don't necessarily need the full fledge JavaEE application server to support.
 You can run it over simple servlet container like Tomcat.
 *
 */
@Service
public class EngService {

    private static Logger log = LoggerFactory.getLogger(EngService.class);

    /*************************************************************************
     MULTI-PURPOSE
     *************************************************************************/
    /**
     * @return
     */
    public String doReactTest() {
        return "TEST ENGINE1";
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
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

            final String jsonstr = new String(Files.readAllBytes(path));

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

    public List<DBItem1> findAllOrderedByNameDescending(){
        List<DBItem1> items = (List<DBItem1>) data1Repo.findAllOrderedByNameDescending();
        log.info("OK");
        return items;
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

}
