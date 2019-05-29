package com.core.eng;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class EngTask {

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @param index
     * @param value
     */
    public synchronized void setActd(final int index, final boolean value){
        actd[index].set(value);
    }
    private AtomicBoolean[] actd = new AtomicBoolean[]{
        new AtomicBoolean(false),
        new AtomicBoolean(false),
        new AtomicBoolean(false)
    };

    /**
     *
     */
    private enum TaskStatus {
        TASKSTATUS_INIT,
        TASKSTATUS_EXECUTE
    }
    private TaskStatus[] tasksStatus = {
        TaskStatus.TASKSTATUS_INIT,
        TaskStatus.TASKSTATUS_INIT,
        TaskStatus.TASKSTATUS_INIT
    };

    /**
     *
     */
    @Autowired
    private EngServiceDB pageService;

    /**
     *
     */
    @Scheduled(initialDelay = 300, fixedDelay=300)
    public void execute1() {
        try {
            final int index = 0;

            if (false == actd[index].get()) {
                return;
            }

            switch(tasksStatus[index]){
                case TASKSTATUS_INIT:
                    log.info("Starting speedy task");
                    pageService.findItemTestById(getCount().intValue() - 1);
                    tasksStatus[index] = TaskStatus.TASKSTATUS_EXECUTE;
                    break;
                case TASKSTATUS_EXECUTE:
                    log.info("Running speedy task");
                    pageService.findItemTestByName("NAME" + getCount());
                    break;
            }

        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     *
     */
    @Scheduled(initialDelay = 3000, fixedDelay=3000)
    public void execute2() {
        try {
            final int index = 1;

            if (false == actd[index].get()) {
                return;
            }

            switch(tasksStatus[index]){
                case TASKSTATUS_INIT:
                    log.info("Starting engine from task");
                    pageService.addOneItemTest(getCount().intValue(), "NAME" + getCount().addAndGet(1));
                    tasksStatus[index] = TaskStatus.TASKSTATUS_EXECUTE;
                    break;
                case TASKSTATUS_EXECUTE:
                    log.info("Running engine from task");
                    pageService.findAllItemTestOrderedByNameDescending().forEach(
                        (it)-> log.info(String.format("DBItemTest ID: %s, NAME: %s<br>", it.getId(), it.getName()))
                    );
                    break;
            }
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     *
     */
    @Scheduled(initialDelay = 10000, fixedDelay=10000)
    public void execute3() {
        try {
            final int index = 2;

            if (false == actd[index].get()) {
                return;
            }

            switch(tasksStatus[index]){
                case TASKSTATUS_INIT:
                    log.info("Starting ing engine from SLOW task");
                    pageService.updateOneItemTest(getCount().intValue() - 1, "NAME" + getCount().intValue() + 1);
                    tasksStatus[index] = TaskStatus.TASKSTATUS_EXECUTE;
                    break;
                case TASKSTATUS_EXECUTE:
                    log.info("Running engine from SLOW task");
                    pageService.findAllItemTestOrderedByNameDescending().forEach(
                        (it)-> log.info(String.format("DBItemTest ID: %s, NAME: %s<br>", it.getId(), it.getName()))
                    );
                    break;
            }
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }

    }

}
