package com.costin.disertatie.api.event;

public class AccountDebitedEvent extends BaseEvent<String> {
    public final double debitAmount;
    public final String currency;

    public AccountDebitedEvent(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
