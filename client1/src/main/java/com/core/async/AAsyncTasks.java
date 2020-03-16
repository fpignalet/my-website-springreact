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
    @Scheduled(initialDelay = 5000, fixedDelay=5000)
    protected void monitor() {
        try {
            final String ctxData = ((AAsyncTask.AsyncContext)taskSpeed.getContext().get()).getData();
            if(AAsyncTask.BEGIN_OK.equals(ctxData)){
                log.info(ctxData);
            }
            else if(AAsyncTask.EXECUTE_OK.equals(ctxData)){
                log.info(ctxData);
            }
            else if(AAsyncTask.TERMINATE_OK.equals(ctxData)){
                log.info(ctxData);
            }
            else {
                log.info(AAsyncTask.SOMETHING_WENT_WRONG);
            }
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
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

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private AsyncTaskSpeed taskSpeed;
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private AsyncTaskStandard taskStandard;
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private AsyncTaskSlow taskSlow;

}
