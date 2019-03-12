package com.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 */
@Controller
public abstract class ACoreControllerBase {

    protected static Logger log = LoggerFactory.getLogger(EngTask.class);

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
    public ACoreControllerBase(EngService engine1) {
        this.engine1 = engine1;
    }

}
