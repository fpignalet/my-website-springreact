package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class EngTask {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private EngServiceDB pageService;

    @Scheduled(initialDelay = 300, fixedDelay=300)
    public void execute1() {
        try {
            log.info("Running speedy task");

            pageService.findById(getCount().intValue() - 1);
            pageService.findByName("NAME" + getCount());

        }
        catch (Exception e) {
            log.error("execute1 failed: " + e.getMessage());
        }
    }

    @Scheduled(initialDelay = 3000, fixedDelay=3000)
    public void execute2() {
        try {
            log.info("Running engine from task");

            pageService.addOneItem1(getCount().intValue(), "NAME" + getCount().addAndGet(1));

            pageService.findAllOrderedByNameDescending().forEach(
                (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
            );
        }
        catch (Exception e) {
            log.error("execute2 failed: " + e.getMessage());
        }
    }

    @Scheduled(initialDelay = 10000, fixedDelay=10000)
    public void execute3() {
        try {
            log.info("Running engine from SLOW task");

            pageService.updateOneItem1(getCount().intValue() - 1, "NAME" + getCount().intValue() + 1);

            pageService.findAllOrderedByNameDescending().forEach(
                    (it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
            );
        }
        catch (Exception e) {
            log.error("execute3 failed: " + e.getMessage());
        }

    }

}
