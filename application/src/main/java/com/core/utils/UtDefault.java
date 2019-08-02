package com.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Slf4j
public class UtDefault {

    @Autowired
    private Environment env;

    /**
     * @brief access spring application properties
     * UNDER CONSTRUCTION
     */
    public void accessEnv() {
        final String paths[] = {
            env.getProperty("spring.application.name"),
            env.getProperty("spring.jpa.hibernate.ddl-auto"),
            env.getProperty("spring.jpa.database-platform"),
            env.getProperty("spring.data.rest.base-path"),
            env.getProperty("spring.datasource.driverClassName"),
            env.getProperty("spring.datasource.username"),
            env.getProperty("spring.datasource.password"),
            env.getProperty("spring.datasource.url"),
            env.getProperty("spring.mail.host"),
            env.getProperty("spring.mail.port"),
            env.getProperty("spring.mail.username"),
            env.getProperty("spring.mail.password"),
            env.getProperty("spring.mail.properties.mail.smtp.auth"),
            env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"),
            env.getProperty("server.port"),
            env.getProperty("server.ssl.key-store"),
            env.getProperty("server.ssl.key-store-password"),
            env.getProperty("server.ssl.key-password")
        };

        log.debug(paths.toString());
    }

}
