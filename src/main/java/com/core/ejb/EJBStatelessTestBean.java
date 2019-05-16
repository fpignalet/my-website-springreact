package com.core.ejb;

import org.springframework.stereotype.Component;

import javax.ejb.Stateless;

@Component
@Stateless(name = "EJBStatelessTest")
public class EJBStatelessTestBean implements IEJBStatelessTest {

    public String getHelloWorld() {
        return "Hello Stateless World!";
    }

}