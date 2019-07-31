package com.core.async.impl;

import com.core.async.AAsyncTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.core.async.EAsyncItems.SPEEDY;

@Slf4j
@Component
public class AsyncTaskSpeed extends AAsyncTask {

    /**
     * @brief constructor
     */
    public AsyncTaskSpeed() {
        super(SPEEDY);
    }

    @Override
    public boolean begin() {
        ((AsyncContext)getContext().get()).setData("BEGIN OK");

        if(null != getEngineDB().findItemTestById(getCount().intValue() - 1)){
            return true;
        }

        return false;
    }

    @Override
    public boolean execute() {
        ((AsyncContext)getContext().get()).setData("EXECUTE OK");

        if(null != getEngineDB().findItemTestByName("NAME" + getCount())){
            return true;
        }

        return false;
    }

    @Override
    public boolean terminate() {
        ((AsyncContext)getContext().get()).setData("TERMINATE OK");
        return true;
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

}
