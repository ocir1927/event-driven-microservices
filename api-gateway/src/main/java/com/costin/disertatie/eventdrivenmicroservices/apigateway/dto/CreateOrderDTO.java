package com.costin.disertatie.eventdrivenmicroservices.apigateway.dto;

public class CreateOrderDTO {
    private String accountId;
    private String stockId;
    private double value;
    private double price;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(String accountId, String stockId, double value, double price) {
        this.accountId = accountId;
        this.stockId = stockId;
        this.value = value;
        this.price = price;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
