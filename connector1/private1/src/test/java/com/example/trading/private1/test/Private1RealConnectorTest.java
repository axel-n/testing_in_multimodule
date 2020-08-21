package com.example.trading.private1.test;

import com.example.trading.common.PrivateBaseTest;
import com.example.trading.common.RedisSenderMock;
import com.example.trading.common.data.Order;
import com.example.trading.common.data.OrderStatus;
import com.example.trading.private1.Private1Connector;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@Import(TestConfig.class)
@PropertySource("classpath:private1.properties")
public class Private1RealConnectorTest extends PrivateBaseTest {

    @Value("${EXCHANGE}")
    private String exchange;

    @Autowired
    private Private1Connector private1Connector;

    @Autowired
    private RedisSenderMock redisSenderMock;

    private final Order newOrder = new Order();
    private final Order badOrder = new Order();

    @PostConstruct
    private void setUp() {

        newOrder.setInternalId(UUID.randomUUID().toString());
        newOrder.setStatus(OrderStatus.PendingSubmit);

        badOrder.setInternalId(UUID.randomUUID().toString());
        badOrder.setStatus(OrderStatus.PendingSubmit);

        log.info("setUp started");

        while (private1Connector == null) {
            log.info("wait private1Connector...");
        }

        init(exchange, "BTC/USD", private1Connector, redisSenderMock, newOrder, badOrder);
    }

    @Test
    public void sendGoodOrderAndCancelIt() {
        super.sendGoodOrderAndCancelIt();
    }
}
