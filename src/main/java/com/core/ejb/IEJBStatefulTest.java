package com.core.ejb;

import javax.ejb.Remote;

@Remote
public interface IEJBStatefulTest {
    int howManyTimes();
    String getHelloWorld();
}
