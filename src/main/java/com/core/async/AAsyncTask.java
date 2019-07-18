package com.core.async;

import com.core.eng.impl.EngServiceDBTest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A base task scheduler which calls
 * <ul>
 *     <li><code>begin</code></li>
 *     <li><code>execute</code></li>
 *     <li><code>terminate</code></li>
 * </ul>
 */
@Slf4j
public abstract class AAsyncTask {

    /**
     * @brief task scheduling entrypoint
     */
    public void entry() {
        try {
            if (false == getActive().get()) {
                return;
            }

            switch(getStatus()){

                case TASKSTATUS_INIT:
                    if(true == begin()){
                        setStatus(EAsyncStatus.TASKSTATUS_EXECUTE);
                        log.info("begin finished");
                        break;
                    }

                    log.info("begin not finished");
                    break;

                case TASKSTATUS_EXECUTE:
                    if(true == execute()){
                        setStatus(EAsyncStatus.TASKSTATUS_DONE);
                        log.info("begin finished");
                        break;
                    }

                    log.info("begin not finished");
                    break;

                case TASKSTATUS_DONE:
                    if(true == terminate()){
                        if(true == getResetable().get()) {
                            setStatus(EAsyncStatus.TASKSTATUS_INIT);
                        }

                        log.info("terminate finished");
                    }

                    log.info("terminate not finished");
                    break;
            }
        }
        catch (Exception e) {
            log.error("failed: " + e.getMessage());
        }

    }

    public abstract boolean begin ();
    public abstract boolean execute ();
    public abstract boolean terminate ();
    protected AAsyncTask(EAsyncItems taskId) {
        this.taskId = taskId;
    }
    @Getter(AccessLevel.PUBLIC)
    private final EAsyncItems taskId;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private EngServiceDBTest engineDB;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private EAsyncStatus status = EAsyncStatus.TASKSTATUS_INIT;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicBoolean active = new AtomicBoolean(false);

    /**
     * when status == TASKSTATUS_DONE, restart Task
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AtomicBoolean resetable = new AtomicBoolean(true);

}
