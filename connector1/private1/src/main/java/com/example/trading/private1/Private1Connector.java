package com.example.trading.private1;

import com.example.trading.common.data.Order;
import com.example.trading.common.interfaces.IPrivateApiClient;
import com.example.trading.common.interfaces.IPrivateConnector;
import com.example.trading.common.interfaces.IRedisSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class Private1Connector implements IPrivateConnector {

    private final IPrivateApiClient apiClient;
    private final IRedisSender redisSender;

    public Private1Connector(IPrivateApiClient apiClient, IRedisSender redisSender) {
        this.apiClient = apiClient;
        this.redisSender = redisSender;
    }


    @Override
    public Order sendOrder(Order order) {
        Order response = apiClient.sendOrder(order);
        redisSender.send(order.toString());

        return response;
    }

    @Override
    public Order cancelOrder(Order order) {
        Order response = apiClient.cancelOrder(order);
        redisSender.send(order.toString());

        return response;
    }

    @Override
    public Order getOrderStatus(Order order) {
        Order response = apiClient.getOrderStatus(order);
        redisSender.send(order.toString());

        return response;
    }

    @Scheduled(fixedDelay = 10_000)
    private void sendKeepAlive() {
    }
}
