package com.costin.disertatie.api.event;

public class AccountCreditedEvent extends BaseEvent<String> {

    public final double creditAmount;

    public final String currency;

    public AccountCreditedEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}