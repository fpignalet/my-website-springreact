package com.core.async.impl;

import com.core.async.AAsyncTasks;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * example tasks handler
 */
@Service
@ComponentScan({"com.core.eng", "com.core.data"})
public class AsyncServiceTasks extends AAsyncTasks {
    /**
     * @brief constructor
     * @param taskSpeed    autowired EngTaskSpeed
     * @param taskStandard autowired EngTaskStandard
     * @param taskSlow     autowired EngTaskSlow
     */
    public AsyncServiceTasks(AsyncTaskSpeed taskSpeed, AsyncTaskStandard taskStandard, AsyncTaskSlow taskSlow) {
        super(taskSpeed, taskStandard, taskSlow);
    }
}
