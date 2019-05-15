package com.core.ctrl;

import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import com.core.eng.EngServiceMail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    private EngServiceDB engineDB;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceJSON engineJSON;

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private EngServiceMail engineMail;

    /**
     * @param engineDB
     * @param engineJSON
     * @param engineMail
     */
    public AControllerBase(EngServiceDB engineDB, EngServiceJSON engineJSON, EngServiceMail engineMail) {
        this.engineDB = engineDB;
        this.engineJSON = engineJSON;
        this.engineMail = engineMail;
    }

    /**
     * @param name
     * @param model
     */
    protected abstract void updateModel(final String name, final Model model);

}
