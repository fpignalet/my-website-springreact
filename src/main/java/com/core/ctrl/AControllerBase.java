package com.core.ctrl;

import com.core.eng.impl.EngServiceDBABook;
import com.core.eng.impl.EngServiceDBCV;
import com.core.eng.impl.EngServiceDBTest;
import com.core.eng.impl.EngServiceMail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

/**
 * This base controller is mostly a wrapper above Eng... services.
 */
@Slf4j
@Controller
@ComponentScan({"com.core.eng", "com.core.data"})
public abstract class AControllerBase {

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceDBTest engineDB;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceDBCV engineCV;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceDBABook engineContact;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceMail engineMail;

    /**
     * @param engineDB
     * @param engineMail
     */
    public AControllerBase(
        final EngServiceDBTest engineDB,
        final EngServiceDBCV engineCV,
        final EngServiceDBABook engineContact,
        final EngServiceMail engineMail
    ) {
        this.engineDB = engineDB;
        this.engineCV = engineCV;
        this.engineContact = engineContact;

        this.engineMail = engineMail;

    }

}
