package com.costin.disertatie.api.event;

public class OrderPendingEvent extends BaseEvent<String>{
    public final String accountId;
    public final String stockId;
    public final double value;

    public OrderPendingEvent(String id, String accountId, String stockId, double value) {
        super(id);
        this.accountId = accountId;
        this.stockId = stockId;
        this.value = value;
    }
}
