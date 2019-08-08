package com.core.data.impl.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Service
public class RDMessageSubscriber implements MessageListener {

    public void onMessage(final Message message, final byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
    }

    private static final List<String> messageList = new ArrayList<String>();

}
