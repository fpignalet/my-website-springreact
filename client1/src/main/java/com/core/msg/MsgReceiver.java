package com.core.msg;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgReceiver {

    @JmsListener(destination = "mailbox", containerFactory = "getJmsFactory")
    public void receiveMessage(MsgEmail email) {
        System.out.println("Received <" + email + ">");
    }

}
