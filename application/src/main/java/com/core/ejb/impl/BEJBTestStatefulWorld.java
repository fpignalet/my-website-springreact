package com.core.ejb.impl;

import com.core.ejb.IEJBTestStateful;

/**
 * @brief UNDER CONSTRUCTION
 */
//import javax.ejb.Remote;
//import javax.ejb.Stateful;
//
//@Stateful(name = "IEJBTestStateful")
public class BEJBTestStatefulWorld implements IEJBTestStateful {

    private int howManyTimes = 0;

    public int howManyTimes() {
        return howManyTimes;
    }

    public String getHelloWorld() {
        howManyTimes++;
        return "Hello Stateful World";
    }
}
