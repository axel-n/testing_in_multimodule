package com.example.trading.common;

import com.example.trading.common.data.Order;
import com.example.trading.common.data.OrderStatus;
import com.example.trading.common.interfaces.IPrivateConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public abstract class PrivateBaseTest {
    private String exchangeName;
    private String symbol;
    private IPrivateConnector privateConnector;
    private RedisSenderMock redisSender;
    private Order newOrder;
    private Order badOrder;

    public void init(String exchangeName, String symbol, IPrivateConnector privateConnector, RedisSenderMock redisSender,
                     Order newOrder,  Order badOrder) {


        Assert.notNull(exchangeName, "exchangeName must not null");
        Assert.notNull(symbol, "symbol must not null");
        Assert.notNull(privateConnector, "privateConnector must not null");
        Assert.notNull(privateConnector, "newOrder must not null");
        Assert.notNull(privateConnector, "badOrder must not null");

        this.exchangeName = exchangeName;
        this.symbol = symbol;
        this.privateConnector = privateConnector;
        this.redisSender = redisSender;
        this.newOrder = newOrder;
        this.badOrder = badOrder;
    }

    public void sendGoodOrderAndCancelIt() {
        Order response = privateConnector.sendOrder(newOrder);
        Assert.notNull(response, "response must be not null");

        Assert.isTrue(response.getStatus().equals(OrderStatus.Submitted), "status must be submitted");

        Order cancelResponse = privateConnector.cancelOrder(response);

        Assert.notNull(cancelResponse, "cancelResponse must be not null");
        Assert.isTrue(response.getStatus().equals(OrderStatus.Cancelled), "status must be cancelled");
    }
}
