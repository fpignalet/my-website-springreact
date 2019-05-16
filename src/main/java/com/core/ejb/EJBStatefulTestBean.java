package com.core.ejb;

import org.springframework.stereotype.Component;

import javax.ejb.Stateful;

@Component
@Stateful(name = "EJBStatefulTest")
public class EJBStatefulTestBean implements IEJBStatefulTest {

    private int howManyTimes = 0;

    public int howManyTimes() {
        return howManyTimes;
    }

    public String getHelloWorld() {
        howManyTimes++;
        return "Hello Stateful World";
    }

}