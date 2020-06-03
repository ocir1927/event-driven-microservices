package com.costin.disertatie.api.entity;

public class OrderDTO {
    private String orderId;

    private String accountId;

    private double value;

    private String stockSymbol;

    private Status status;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String accountId, double value, String stockSymbol, Status status) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.value = value;
        this.stockSymbol = stockSymbol;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
