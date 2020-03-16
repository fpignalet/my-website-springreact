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
public class AsyncTaskStandard extends AAsyncTask {

    /**
     * @brief constructor
     */
    AsyncTaskStandard() {
        super(STANDARD);
    }

    @Override
    public boolean begin() {
        ((AsyncContext)getContext().get()).setData(BEGIN_OK);

        if(null != getEngineDB().addOneItemTest("NAME" + getCount().addAndGet(1))){
            return true;
        }

        return false;
    }

    @Override
    public boolean execute() {
        ((AsyncContext)getContext().get()).setData(EXECUTE_OK);

        getEngineDB().findAllItemTestOrderedByNameAscending().forEach(
            (it)-> log.info(String.format("DBItemTest ID: %s, NAME: %s<br>", it.getId(), it.getName()))
        );

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
