package com.costin.disertatie.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand extends BaseCommand<String>{

    public String accountId;

    public String stockId;

    public CreateOrderCommand(String id, String accountId, String stockId) {
        super(id);
        this.accountId = accountId;
        this.stockId = stockId;
    }

}
