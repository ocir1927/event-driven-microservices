package com.costin.disertatie.api.event;

public class AccountDebitedEvent extends BaseEvent<String> {
    public final double debitAmount;
    public final String transactionId;
    public final String currency;

    public AccountDebitedEvent(String id,String transactionId, double debitAmount, String currency) {
        super(id);
        this.transactionId = transactionId;
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
