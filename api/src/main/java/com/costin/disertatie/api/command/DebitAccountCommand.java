package com.costin.disertatie.api.command;

public class DebitAccountCommand extends BaseCommand<String>{

    public final double debitAmount;

    public final String transactionId;

    public final String currency;

    public DebitAccountCommand(String id, double debitAmount, String transactionId, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.transactionId = transactionId;
        this.currency = currency;
    }

}
