package com.costin.disertatie.orderprojection.query;

import com.costin.disertatie.api.entity.Status;
import org.axonframework.modelling.command.AggregateIdentifier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class OrderEntity {

    @Id
    private String orderId;

    private String accountId;

    private double value;

    private String stockSymbol;

    private Status status;

    private double price;

    public OrderEntity() {
    }

    public OrderEntity(String orderId, String accountId, double value, String stockSymbol, Status status, double price) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.value = value;
        this.stockSymbol = stockSymbol;
        this.status = status;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

