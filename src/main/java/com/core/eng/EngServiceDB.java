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
public class EngServiceDB {

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
        final List<DBItem1> items = data1Repo.findById(id);
        final DBItem1 item = items.get(0);
        item.setName(name);
        data1Repo.save(item);
        log.info("OK");
        return "NEW ITEM 1 UPDATED";
    }

    /**
     * @return
     */
    public List<DBItem1> findById(final int id){
        List<DBItem1> items = (List<DBItem1>) data1Repo.findById(id);

        items.forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
        );

        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItem1> findByName(final String name){
        List<DBItem1> items = (List<DBItem1>) data1Repo.findByName(name);

        items.forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
        );

        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public String findAllItems() {
        final StringBuilder result = new StringBuilder();

        data1Repo.findAll().forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
        );

        log.info("OK");
        return result.toString();
    }

    /**
     * @return
     */
    public List<DBItem1> findAllOrderedByNameDescending(){
        List<DBItem1> items = (List<DBItem1>) data1Repo.findAllOrderedByNameDescending();

        items.forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
        );

        log.info("OK");
        return items;
    }

    /*************************************************************************
     INIT PART
     *************************************************************************/
    /**
     * @param data1Repo
     */
    public EngServiceDB(final DBItem1DAO data1Repo) {
        this.data1Repo = data1Repo;
        log.info("OK");
    }

    /**
     *
     */
    private static Logger log = LoggerFactory.getLogger(EngServiceDB.class);

}
