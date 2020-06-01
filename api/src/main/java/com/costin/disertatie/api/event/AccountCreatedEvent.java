package com.costin.disertatie.api.event;

public class AccountCreatedEvent extends BaseEvent<String> {

    public final double accountBalance;
    public final String currency;
    public final String owner;

    public AccountCreatedEvent(String id, double accountBalance, String currency, String owner) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.owner = owner;
    }
}
