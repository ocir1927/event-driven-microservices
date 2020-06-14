export class CreateOrderDto{
    accountId: string;
    stockId: string;
    value: number;
    price: number;

    constructor(accountId, stockId, value, price){
        this.stockId = stockId;
        this.accountId = accountId;
        this.value = value;
        this.price = price;
    }
}