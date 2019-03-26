package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EngTask {

    private static Logger log = LoggerFactory.getLogger(EngTask.class);

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private EngService pageService;

    @Scheduled(initialDelay = 3000, fixedDelay=3000)
    public void execute1() {
        try {
            pageService.addOneItem1(getCount().intValue(), "[EXE1]NAME" + getCount().addAndGet(1));

            log.info("Running engine from task");
            pageService.findAllOrderedByNameDescending().forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
            );
        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

    @Scheduled(initialDelay = 300, fixedDelay=300)
    public void execute2() {
        try {
            log.info("Running speedy task");
        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

    @Scheduled(initialDelay = 10000, fixedDelay=10000)
    public void execute3() {
        try {
            pageService.addOneItem1(getCount().intValue(), "[EXE3]NAME" + getCount().addAndGet(1));

            log.info("Running engine from SLOW task");
            pageService.findAllOrderedByNameDescending().forEach(
                    (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
            );
        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

}
