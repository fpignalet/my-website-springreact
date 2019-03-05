package com.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public abstract class ACoreControllerBase {

    /**
     *
     */
    @Autowired
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private CoreEngine1 engine1;

    /**
     * @param engine1
     */
    public ACoreControllerBase(CoreEngine1 engine1) {
        this.engine1 = engine1;
    }

}
