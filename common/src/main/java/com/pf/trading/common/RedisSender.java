package com.pf.trading.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisSender implements IRedisSender {
    public void send(String message) {
        log.info("standard RedisSender. send message: {}", message);
    }
}
