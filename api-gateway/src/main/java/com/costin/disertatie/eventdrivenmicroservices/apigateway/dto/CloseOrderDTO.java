package com.costin.disertatie.eventdrivenmicroservices.apigateway.dto;

public class CloseOrderDTO {

    private String orderId;
    private String stockSymbol;
    private double invested;
    private double currentValue;
    private double profitLoss;

    public CloseOrderDTO() {
    }

    public CloseOrderDTO(String orderId, String stockSymbol, double invested, double currentValue, double profitLoss) {
        this.orderId = orderId;
        this.stockSymbol = stockSymbol;
        this.invested = invested;
        this.currentValue = currentValue;
        this.profitLoss = profitLoss;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getInvested() {
        return invested;
    }

    public void setInvested(double invested) {
        this.invested = invested;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }
}
