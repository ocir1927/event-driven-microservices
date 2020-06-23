package com.costin.disertatie.api.event;

public class OrderClosedEvent {
    public String orderId;
    public String accountId;
    public double finalValue;

    public OrderClosedEvent(String orderId, String accountId, double finalValue) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.finalValue = finalValue;
    }
}
