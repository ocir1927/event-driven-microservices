package com.costin.disertatie.api.command;

import com.costin.disertatie.api.event.BaseEvent;

import java.util.List;

public class OrderPaidEvent extends BaseEvent<String> {

    public OrderPaidEvent(String orderId) {
        super(orderId);
    }
}
