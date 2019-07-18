package com.core.async;

import com.core.async.impl.AsyncTaskSlow;
import com.core.async.impl.AsyncTaskSpeed;
import com.core.async.impl.AsyncTaskStandard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public abstract class AAsyncTasks {

    /**
     * @param task is the task name
     */
    public void activate(final String task) {
        getTaskSpeed().getActive().set(task.contains(EAsyncItems.SPEEDY.getName()));
        getTaskStandard().getActive().set(task.contains(EAsyncItems.STANDARD.getName()));
        getTaskSlow().getActive().set(task.contains(EAsyncItems.SLOW.getName()));
    }

    /**
     * execution of the example EngTaskSpeed
     */
    @Scheduled(initialDelay = 300, fixedDelay=300)
    protected void executeSpeedyTask() {
        try {
            taskSpeed.entry();
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     * execution of the example EngTaskStandard
     */
    @Scheduled(initialDelay = 3000, fixedDelay=3000)
    protected void executeStandardTask() {
        try {
            taskStandard.entry();
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     * execution of the example EngTaskSlow
     */
    @Scheduled(initialDelay = 10000, fixedDelay=10000)
    protected void executeSlowTask() {
        try {
            taskSlow.entry();
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }

    }

    /**
     * @brief constructor
     * @param taskSpeed autowired EngTaskSpeed
     * @param taskStandard autowired EngTaskStandard
     * @param taskSlow autowired EngTaskSlow
     */
    public AAsyncTasks(final AsyncTaskSpeed taskSpeed, final AsyncTaskStandard taskStandard, final AsyncTaskSlow taskSlow) {
        this.taskSpeed = taskSpeed;
        this.taskStandard = taskStandard;
        this.taskSlow = taskSlow;
    }

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private final AsyncTaskSpeed taskSpeed;
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private final AsyncTaskStandard taskStandard;
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private final AsyncTaskSlow taskSlow;

}
