package com.pf.trading.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisSenderMock implements IRedisSender {

    private String lastMessage = "";

    public void send(String message) {
        log.info("mock RedisSender. send message: {}", message);
        lastMessage = message;
    }


    public String getLastMessage() {
        return lastMessage;
    }
}
