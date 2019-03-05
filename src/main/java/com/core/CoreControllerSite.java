package com.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class CoreControllerSite extends ACoreControllerBase {

    /**
     * @param engine1
     */
    public CoreControllerSite(CoreEngine1 engine1) {
        super(engine1);
    }

}
