package com.core.async.impl;

import lombok.extern.slf4j.Slf4j;
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

    @Bean
    public TaskScheduler poolScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

    // We can have multiple tasks inside the same registrar as we can see below.
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (null == scheduledTaskRegistrar) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (null == taskRegistrar.getScheduler()) {
            taskRegistrar.setScheduler(poolScheduler());
        }

        runScheduledFixed(taskRegistrar);
        // or cron way
        runScheduledCron(taskRegistrar);
    }

    protected void runScheduledFixed(ScheduledTaskRegistrar taskRegistrar) {
        future = taskRegistrar.getScheduler().schedule(() -> scheduleFixed(), t -> {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = t.lastActualExecutionTime();
            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
            nextExecutionTime.add(Calendar.SECOND, 7);
            return nextExecutionTime.getTime();
        });
    }

    protected void runScheduledCron(ScheduledTaskRegistrar taskRegistrar) {
        final CronTrigger croneTrigger = new CronTrigger("0/10 * * * * ?", TimeZone.getDefault());
        future = taskRegistrar.getScheduler().schedule(() -> scheduleCron("0/10 * * * * ?"), croneTrigger);
    }

    public void scheduleFixed() {
        log.info("scheduleFixed: Next execution time of this will always be 5 seconds");
    }

    public void scheduleCron(String cron) {
        log.info("scheduleCron: Next execution time of this taken from cron expression -> {}", cron);
    }

    /**
     * @param mayInterruptIfRunning {@code true} if the thread executing this task
     * should be interrupted; otherwise, in-progress tasks are allowed to complete
     */
    public void cancelTasks(boolean mayInterruptIfRunning) {
        log.info("Cancelling all tasks");
        future.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
    }

    public void activateScheduler() {
        log.info("Re-Activating Scheduler");
        configureTasks(scheduledTaskRegistrar);
    }

    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    private ScheduledFuture future;

}