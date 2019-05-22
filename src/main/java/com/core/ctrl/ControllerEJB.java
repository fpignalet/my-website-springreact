package com.core.ctrl;

import com.core.ejb.IEJBStatefulTest;
import com.core.ejb.IEJBStatelessTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UNDER CONSTRUCTION
 */
@RestController
@ComponentScan({"com.core.ejb"})
public class ControllerEJB {

    @Autowired
    final IEJBStatelessTest statelessTest;

    @Autowired
    final IEJBStatefulTest statefulTest;

    /**
     * @param statelessTest
     * @param statefulTest
     */
    public ControllerEJB(IEJBStatelessTest statelessTest, IEJBStatefulTest statefulTest) {
        this.statelessTest = statelessTest;
        this.statefulTest = statefulTest;
    }

    /**
     * @return
     */
    @GetMapping("/ejbstateless")
    public String getStateless() {
        return statelessTest.getHelloWorld();
    }

    /**
     * @return
     */
    @GetMapping("/ejbstateful")
    public String getStateful() {
        return statefulTest.getHelloWorld()
                + " called " + statefulTest.howManyTimes() + " times";
    }

}