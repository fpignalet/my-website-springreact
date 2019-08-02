package com.core.async.impl;

import com.core.async.AAsyncTasks;
import org.springframework.stereotype.Service;

/**
 * example tasks handler
 */
@Service
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
