package com.costin.disertatie.api.command;

public class CreditAccountCommand extends BaseCommand<String> {

    public final double creditAmount;

    public final String currency;

    public CreditAccountCommand(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
