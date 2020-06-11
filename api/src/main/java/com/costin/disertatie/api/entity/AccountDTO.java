package com.costin.disertatie.api.entity;

public class AccountDTO {

    private String accountId;

    private double accountBalance;

    private String currency;

    private String status;


    public AccountDTO(String accountId, double accountBalance, String currency, String status) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
