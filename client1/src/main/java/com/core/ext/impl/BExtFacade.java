package com.core.ext.impl;

import com.core.ext.ExtFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
    public void testSerial() throws IOException {
        Runtime.getRuntime().exec("socat pty,raw,echo=0,link=~/dev/fakesrd0 pty,raw,echo=0,link=~/dev/fakesrd1\n");
        new Thread(() -> {
            testSerialOut();
        }).start();
/*
        new Thread(() -> {
            testSerialIn();
        }).start();
*/
    }

    /**
     *
     */
    public void testSerialOut() {
        final Com dataOut =  new Com("~/dev/fakesrd0", Com.Way.wayOUT);
        dataOut.bufferOut = "TOTOTO TESTTESTTESTTEST";
        testWriteSerial(dataOut);
//        System.out.println(dataIn.);
    }

    /**
     *
     */
    public void testSerialIn() {
        final Com dataIn =  new Com("~/dev/fakesrd1", Com.Way.wayIN);
        testReadSerial(dataIn);
//        System.out.println(dataIn.);
    }

}
