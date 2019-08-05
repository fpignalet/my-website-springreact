package com.srv.ejb.impl;

import com.srv.ejb.IEJBTestStateless;

/**
 * @brief UNDER CONSTRUCTION
 */
//import javax.ejb.Remote;
//import javax.ejb.Stateless;
//
//@Stateless(name = "IEJBTestStateless")
public class BEJBTestStateless implements IEJBTestStateless {

    public String getHelloWorld() {
        return "Hello Stateless World!";
    }
}
