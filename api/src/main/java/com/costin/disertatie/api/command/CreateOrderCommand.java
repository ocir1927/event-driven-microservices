package com.costin.disertatie.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand extends BaseCommand<String>{

    @TargetAggregateIdentifier
    public String orderId;

    public String accountId;

    public String stockId;

    public CreateOrderCommand(String id, String orderId, String accountId, String stockId) {
        super(id);
        this.orderId = orderId;
        this.accountId = accountId;
        this.stockId = stockId;
    }

}
