package com.costin.disertatie.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CloseOrderCommamnd {

    @TargetAggregateIdentifier
    public String orderId;

    public double finalValue;

    public CloseOrderCommamnd(String orderId, double finalValue) {
        this.orderId = orderId;
        this.finalValue = finalValue;
    }


}
