package com.core.ejb;

import javax.ejb.Remote;

@Remote
public interface IEJBStatelessTest {
    String getHelloWorld();
}
