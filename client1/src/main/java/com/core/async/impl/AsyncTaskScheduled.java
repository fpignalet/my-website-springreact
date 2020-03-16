package com.core.async.impl;

import com.core.async.AAsyncTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.core.async.EAsyncItems.STANDARD;

@Slf4j
@Component
public class AsyncTaskScheduled extends AAsyncTask {

    /**
     * @brief constructor
     */
    AsyncTaskScheduled() {
        super(STANDARD);
    }

    @Override
    public boolean begin() {
        ((AsyncContext)getContext().get()).setData(BEGIN_OK);

        return true;
    }

    @Override
    public boolean execute() {
        ((AsyncContext)getContext().get()).setData(EXECUTE_OK);

        return true;
    }

    @Override
    public boolean terminate() {
        ((AsyncContext)getContext().get()).setData(TERMINATE_OK);

        return true;
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

}
