package com.costin.disertatie.api.command;

public class DebitAccountCommand extends BaseCommand<String>{

    public final double debitAmount;

    public final String currency;

    public DebitAccountCommand(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }

}
