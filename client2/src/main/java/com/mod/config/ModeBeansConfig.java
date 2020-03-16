package com.mod.config;

import com.lib.Library;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModeBeansConfig {

    @Bean
    protected Library getLibrary() { return new Library(); }

}
