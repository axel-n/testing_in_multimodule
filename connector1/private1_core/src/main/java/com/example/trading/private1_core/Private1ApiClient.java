package com.example.trading.private1_core;

import com.example.trading.common.data.Order;
import com.example.trading.common.data.OrderStatus;
import com.example.trading.common.data.RestResponse;
import com.example.trading.common.interfaces.IPrivateApiClient;
import com.example.trading.common.interfaces.IRequestSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class Private1ApiClient implements IPrivateApiClient {

    private final IRequestSender requestSender;
    private final Map<String, String> headers = new HashMap<>();
    private final String exchangeBaseUrl = "http://127.0.0.1:123/api/v1";

    public Private1ApiClient(IRequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public Order sendOrder(Order order) {
        log.info("try to sendOrder {}", order);

        Map<String, Object> orderForExchange = new HashMap<>();
        orderForExchange.put("client_id", order.getInternalId());

        RestResponse response = requestSender.sendRequest(HttpMethod.POST, exchangeBaseUrl + "/place_order", headers, orderForExchange);
        log.info("sendOrder response {}", response);

        if (response.isSuccess()) {
            order.setExchangeId(UUID.randomUUID().toString());
            order.setStatus(OrderStatus.Submitted);
        } else {
            // TODO some checks
            order.setStatus(OrderStatus.Inactive);
        }

        return order;
    }

    @Override
    public Order cancelOrder(Order order) {
        log.info("try to cancelOrder {}", order);

        Map<String, Object> orderForExchange = new HashMap<>();
        orderForExchange.put("id", order.getExchangeId());
        RestResponse response = requestSender.sendRequest(HttpMethod.DELETE, exchangeBaseUrl + "/cancel_order", headers, orderForExchange);
        log.info("cancelOrder response {}", response);

        if (response.isSuccess()) {
            // TODO some checks
            order.setStatus(OrderStatus.Cancelled);
        }

        return order;
    }

    @Override
    public Order getOrderStatus(Order order) {
        log.info("try to getOrderStatus {}", order);

        String url = exchangeBaseUrl + "/order?id=" + order.getExchangeId();
        RestResponse response = requestSender.sendRequest(HttpMethod.GET, url, headers);
        log.info("cancelOrder response {}", response);

        if (response.isSuccess()) {
            // TODO add some checks
            order.setStatus(OrderStatus.Cancelled);
        }

        return order;
    }
}
