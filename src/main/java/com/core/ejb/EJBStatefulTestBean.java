package com.core.ejb;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.ejb.Stateful;

@Component
@Stateful(name = "EJBStatefulTest")
public class EJBStatefulTestBean implements IEJBStatefulTest {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int howManyTimes = 0;

    public String getMessage() {
        howManyTimes++;
        return "Hello Stateful World";
    }

}