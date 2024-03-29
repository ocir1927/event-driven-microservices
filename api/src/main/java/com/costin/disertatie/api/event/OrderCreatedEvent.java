package com.costin.disertatie.api.event;

import com.costin.disertatie.api.entity.Status;

public class OrderCreatedEvent {
    public final String id;
    public final String accountId;
    public final String stockId;
    public final double value;
    public final Status status;
    public final double price;

    public OrderCreatedEvent(String id, String accountId, String stockId, double value, Status status, double price) {
        this.id = id;
        this.accountId = accountId;
        this.stockId = stockId;
        this.value = value;
        this.status = status;
        this.price = price;
    }
}
