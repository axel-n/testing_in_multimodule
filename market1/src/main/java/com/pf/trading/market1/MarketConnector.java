package com.pf.trading.market1;

import com.pf.trading.common.IMarketConnector;
import com.pf.trading.common.IRedisSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Slf4j
@Service
@EnableScheduling
public class MarketConnector implements IMarketConnector {

    public MarketConnector(IRedisSender redisSender) {
        this.redisSender = redisSender;
    }

    @PostConstruct
    private void setUp() {
        log.info("MarketConnector1 loaded");

        log.info("exchangeName {}", exchangeName);
        log.info("commonServer {}", commonServer);
        log.info("commonKey {}", commonKey);
        log.info("uniqueKeyForMarket1 {}", uniqueKeyForMarket1);
    }

    private final IRedisSender redisSender;

    @Scheduled(fixedDelay = 500)
    public void sendMockMessages() {
        redisSender.send("some message. now " + System.currentTimeMillis());
    }

    @Value("${EXCHANGE}")
    private String exchangeName;

    @Value("${COMMON_SERVER}")
    private String commonServer;

    @Value("${COMMON_KEY}")
    private String commonKey;

    @Value("${UNIQUE_KEY_FOR_THIS_EXCHANGE}")
    private String uniqueKeyForMarket1;
}
