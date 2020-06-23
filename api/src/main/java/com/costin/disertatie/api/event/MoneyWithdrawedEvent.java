package com.costin.disertatie.api.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MoneyWithdrawedEvent {

    public String accountId;
    public double withdrawAmmount;
    public String bankAccount;
    public String ownerName;

    public MoneyWithdrawedEvent(String accountId, double withdrawAmmount, String bankAccount, String ownerName) {
        this.accountId = accountId;
        this.withdrawAmmount = withdrawAmmount;
        this.bankAccount = bankAccount;
        this.ownerName = ownerName;
    }
}
