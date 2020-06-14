package com.costin.disertatie.api.command;


public class CreateNewOrderCommand extends BaseCommand<String> {

    public String accountId;

    public String stockId;

    public double value;

    public double price;

    public CreateNewOrderCommand(String id, String accountId, String stockId, double value, double price) {
        super(id);
        this.accountId = accountId;
        this.stockId = stockId;
        this.value = value;
        this.price = price;
    }
}
