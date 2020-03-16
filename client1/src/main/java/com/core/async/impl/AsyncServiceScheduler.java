package com.core.async.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

/**
 * Alternative version for DynamicScheduler
 * This one should support everything the basic dynamic scheduler does,
 * and on top of it, you can cancel and re-activate the scheduler.
 */
@Slf4j
@Service
public class AsyncServiceScheduler implements SchedulingConfigurer {

    /**
     * @return
     */
    @Bean
    public TaskScheduler poolScheduler() {
        return getPoolScheduler();
    }

    /**
     *
     */
    public void activateScheduler() {
        log.info("Re-Activating Scheduler");
        configureTasks(scheduledTaskRegistrar);
    }

    /**
     * @param mayInterruptIfRunning {@code true} if the thread executing this task
     * should be interrupted; otherwise, in-progress tasks are allowed to complete
     */
    public void cancelTasks(boolean mayInterruptIfRunning) {
        log.info("Cancelling all tasks");
        futureFixed.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
        futureCroned.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
    }

    /**
     * We can have multiple tasks inside the same registrar as we can see below.
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        if (null == scheduledTaskRegistrar) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (null == taskRegistrar.getScheduler()) {
            taskRegistrar.setScheduler(getPoolScheduler());
        }

//        taskFixed.setTaskid();
        taskFixed.getActive().set(true);
        runScheduledFixed(taskRegistrar);
        taskCroned.getActive().set(true);
        runScheduledCron(taskRegistrar);
    }

    /**
     * @return
     */
    protected TaskScheduler getPoolScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("AsyncServiceScheduler");
        scheduler.setPoolSize(2);
        scheduler.initialize();
        return scheduler;
    }

    /**
     * @param taskRegistrar
     */
    protected void runScheduledFixed(final ScheduledTaskRegistrar taskRegistrar) {
        futureFixed = taskRegistrar.getScheduler().schedule(
            /*execute the task*/() -> scheduleFixed(),
            trigger -> {
                final Date lastActualExecutionTime = trigger.lastActualExecutionTime();

                final Calendar nextExecutionTime = new GregorianCalendar();
                nextExecutionTime.setTime(lastActualExecutionTime != null? lastActualExecutionTime: new Date());
                nextExecutionTime.add(Calendar.SECOND, 5);
                return nextExecutionTime.getTime();
            }
        );
    }

    /**
     * @param taskRegistrar
     */
    protected void runScheduledCron(final ScheduledTaskRegistrar taskRegistrar) {
        final String cron = "0/10 * * * * ?";
        final CronTrigger trigger = new CronTrigger(cron, TimeZone.getDefault());
        futureCroned = taskRegistrar.getScheduler().schedule(
            /*execute the task*/() -> scheduleCroned(cron),
            trigger
        );
    }

    /**
     *
     */
    protected void scheduleFixed() {
        log.info("scheduleFixed: Next execution time of this will always be ~5 seconds");
        try {
            taskFixed.entry();
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     * @param cron
     */
    protected void scheduleCroned(final String cron) {
        log.info("scheduleCroned: Next execution time of this taken from cron expression -> {}", cron);
        try {
            taskCroned.entry();
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }
    }

    /**
     * @param taskFixed
     * @param taskCroned
     */
    public AsyncServiceScheduler(final AsyncTaskScheduled taskFixed, final AsyncTaskScheduled taskCroned) {
        this.taskFixed = taskFixed;
        this.taskCroned = taskCroned;
    }

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private final AsyncTaskScheduled taskFixed;

    private ScheduledFuture futureFixed;

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private final AsyncTaskScheduled taskCroned;

    private ScheduledFuture futureCroned;

}