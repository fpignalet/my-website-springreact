package com.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EngTask {

    private static Logger log = LoggerFactory.getLogger(EngTask.class);

    private int count = 0;

    @Autowired
    private EngService pageService;

    @Scheduled(initialDelay = 3000, fixedDelay=3000)
    public void execute1() {
        try {
            pageService.addOneItem1(count, "NAME" + count);

            log.info("Running engine from task");
            pageService.findAllOrderedByNameDescending().forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
            );

            count++;
        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

    @Scheduled(initialDelay = 300, fixedDelay=300)
    public void execute2() {
        try {
            log.info("Running other task");
        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

}
