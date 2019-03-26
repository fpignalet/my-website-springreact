package com.core.ctrl;

import com.core.eng.EngService;
import com.core.eng.EngTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This base controller is mostly a wrapper above the EngineService.
 */
@Controller
public abstract class AControllerBase {

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngService engine1;

    /**
     * @param engine1
     */
    public AControllerBase(EngService engine1) {
        this.engine1 = engine1;
    }

    /**
     *
     */
    protected static Logger log = LoggerFactory.getLogger(EngTask.class);

}
