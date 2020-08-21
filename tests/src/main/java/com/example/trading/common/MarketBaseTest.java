package com.example.trading.common;

import com.example.trading.common.interfaces.IMarketConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public abstract class MarketBaseTest {
    private String exchangeName;
    private String symbol;
    private IMarketConnector marketConnector;
    private RedisSenderMock redisSender;

    public void init(String exchangeName, String symbol, IMarketConnector marketConnector, RedisSenderMock redisSender) {
        this.exchangeName = exchangeName;
        this.symbol = symbol;
        this.marketConnector = marketConnector;
        this.redisSender = redisSender;
    }

    public void checkLastMessageTest() {

        // emulation keep connection
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        String lastMessage = redisSender.getLastMessage();
        log.info("lastMessage {}", lastMessage);
        Assert.notNull(lastMessage, "lastMessage must be not null");
    }
}
