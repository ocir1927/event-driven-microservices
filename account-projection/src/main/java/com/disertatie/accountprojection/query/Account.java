package com.disertatie.accountprojection.query;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    private String accountId;

    private double accountBalance;

    private String currency;

    private String status;

    private String owner;

    public Account() {
    }

    public Account(String accountId, double accountBalance, String currency, String status, String owner) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}

