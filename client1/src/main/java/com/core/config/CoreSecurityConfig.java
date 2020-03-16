package com.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class CoreSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//            .anyRequest().permitAll()
//            .and()
//            .csrf().disable();
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("testuser")
            .password("{noop}testpass") // Spring Security 5 requires specifying the password storage format
            .roles("USER");
    }

}