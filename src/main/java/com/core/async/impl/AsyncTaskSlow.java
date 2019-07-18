package com.core.async.impl;

import com.core.async.AAsyncTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.core.async.EAsyncItems.SLOW;

@Slf4j
@Component
public class AsyncTaskSlow extends AAsyncTask {

    /**
     * @brief constructor
     */
    AsyncTaskSlow() {
        super(SLOW);
    }

    @Override
    public boolean begin() {
        if(null != getEngineDB().updateOneItemTest("NAME" + getCount().intValue() + 1)){
            log.debug("ITEM TEST UPDATED");
            return true;
        }

        return false;
    }

    @Override
    public boolean execute() {
        getEngineDB().findAllItemTestOrderedByNameAscending().forEach(
            (it)-> log.info(String.format("DBItemTest ID: %s, NAME: %s<br>", it.getId(), it.getName()))
        );

        return true;
    }

    @Override
    public boolean terminate() {
        return true;
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicInteger count = new AtomicInteger(0);

}
