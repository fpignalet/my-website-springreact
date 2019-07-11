package com.core.ctrl;

import com.core.eng.EngExternal;
import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import com.core.eng.EngServiceMail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

/**
 * This base controller is mostly a wrapper above the EngineService.
 */
@Slf4j
@Controller
public abstract class AControllerBase {

//    @Autowired
//    private Environment env;

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

//        accessEnv();
        final String result = EngExternal.test();
        log.info(result);
    }

    /**
     *
     */
    protected void accessEnv() {
        final String paths[] = {
//            env.getProperty("spring.application.name"),
//            env.getProperty("spring.jpa.hibernate.ddl-auto"),
//            env.getProperty("spring.jpa.database-platform"),
//            env.getProperty("spring.data.rest.base-path"),
//            env.getProperty("spring.datasource.driverClassName"),
//            env.getProperty("spring.datasource.username"),
//            env.getProperty("spring.datasource.password"),
//            env.getProperty("spring.datasource.url"),
//            env.getProperty("spring.mail.host"),
//            env.getProperty("spring.mail.port"),
//            env.getProperty("spring.mail.username"),
//            env.getProperty("spring.mail.password"),
//            env.getProperty("spring.mail.properties.mail.smtp.auth"),
//            env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"),
//            env.getProperty("server.port"),
//            env.getProperty("server.ssl.key-store"),
//            env.getProperty("server.ssl.key-store-password"),
//            env.getProperty("server.ssl.key-password")
        };

        log.debug(paths.toString());
    }

}
