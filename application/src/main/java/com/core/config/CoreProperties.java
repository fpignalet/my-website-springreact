package com.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.StandardCopyOption;

/**
 * See target/classes/META-INF/spring-configuration-metadata.json
 */
@ConfigurationProperties(prefix = "my")
public class CoreProperties {

    /**
     * String property used to configure my app.
     */
    private String property;

    /**
     * Configuration for file operations.
     */
    private StandardCopyOption copyOption;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public StandardCopyOption getCopyOption() {
        return copyOption;
    }

    public void setCopyOption(StandardCopyOption copyOption) {
        this.copyOption = copyOption;
    }

}
