package com.example.trading.common.data;

import lombok.Data;

@Data
public class Order {
    private OrderStatus status;
    private String internalId;
    private String exchangeId;
}
