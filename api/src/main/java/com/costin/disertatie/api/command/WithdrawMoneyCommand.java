package com.costin.disertatie.api.command;

public class WithdrawMoneyCommand extends BaseCommand<String> {
    public double withdrawAmmount;

    public String bankAccount;

    public String ownerName;

    public WithdrawMoneyCommand(String id, double withdrawAmmount, String bankAccount, String ownerName) {
        super(id);
        this.withdrawAmmount = withdrawAmmount;
        this.bankAccount = bankAccount;
        this.ownerName = ownerName;
    }


}
