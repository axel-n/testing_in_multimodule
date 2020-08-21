package com.example.trading.common.interfaces;


import com.example.trading.common.data.Order;

public interface IPrivateConnector {
    Order sendOrder(Order order);
    Order cancelOrder(Order order);
    Order getOrderStatus(Order order);
}
