package com.costin.disertatie.api.query;

public class GetAllOrdersForAccount {
    private String accountId;

    public GetAllOrdersForAccount(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
