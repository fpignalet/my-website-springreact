package com.core.ext.impl;

import com.core.ext.ExtFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BExtFacade extends ExtFacade {
    /**
     *
     */
    public void testSimple() {
        ///1. TEST with params:
        final Data dataIn =  new Data();
        final String result_execute = testExecute(dataIn);
        System.out.println(result_execute);

        ///2. TEST with complex return value:
        final String[] result_getData = testGetData();
        for(final String dataOut: result_getData){
            System.out.println(dataOut);
        }
    }

    /**
     *
     */
    public void testSerialOut() {
        final Com dataIn =  new Com("/dev/ttyUSB0", Com.Way.wayOUT, 1024, 1024);
        testWriteSerial(dataIn);
//        System.out.println(dataIn.);
    }

    /**
     *
     */
    public void testSerialIn() {
        final Com dataIn =  new Com("/dev/ttyUSB0", Com.Way.wayIN, 1024, 1024);
        testReadSerial(dataIn);
//        System.out.println(dataIn.);
    }

}
