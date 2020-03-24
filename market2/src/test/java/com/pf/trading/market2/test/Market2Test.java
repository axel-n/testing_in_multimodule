package com.pf.trading.market2.test;

import com.pf.trading.common.MarketBaseTest;
import com.pf.trading.common.RedisSenderMock;
import com.pf.trading.market2.MarketConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MarketConnector.class, RedisSenderMock.class})
public class Market2Test extends MarketBaseTest {

    @Autowired
    private MarketConnector marketConnector;

    @Autowired
    private RedisSenderMock redisSenderMock;

    @PostConstruct
    private void init() {
        init("MARKET_2", "CDE", marketConnector, redisSenderMock);
    }

    @Test
    public void checkLastMessage() {
        super.checkLastMessageTest();
    }
}
