package com.costin.disertatie.api.command;

public class CreateAccountCommand extends BaseCommand<String>{

    public final double accountBalance;

    public final String currency;

    public final String owner;

    public CreateAccountCommand(String id, double accountBalance, String currency, String owner) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.owner = owner;
    }
}
