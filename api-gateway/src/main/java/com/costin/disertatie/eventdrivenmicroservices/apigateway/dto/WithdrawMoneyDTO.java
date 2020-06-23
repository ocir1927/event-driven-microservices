package com.costin.disertatie.eventdrivenmicroservices.apigateway.dto;

public class WithdrawMoneyDTO {

    private String accountId;
    private double withdrawAmmount;
    private String bankAccount;
    private String ownerName;

    public WithdrawMoneyDTO() {
    }

    public WithdrawMoneyDTO(String accountId, double withdrawAmmount, String bankAccount, String ownerName) {
        this.accountId = accountId;
        this.withdrawAmmount = withdrawAmmount;
        this.bankAccount = bankAccount;
        this.ownerName = ownerName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getWithdrawAmmount() {
        return withdrawAmmount;
    }

    public void setWithdrawAmmount(double withdrawAmmount) {
        this.withdrawAmmount = withdrawAmmount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
