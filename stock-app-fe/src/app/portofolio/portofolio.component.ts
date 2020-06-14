import { Component, OnInit, OnDestroy } from '@angular/core';
import { PortofolioService } from './portofolio.service';
import { WebSocketAPI } from './websocket.api';
import { StockUpdate } from '../dto/stock.update';
import { OrderService } from './order.service';
import { Order } from '../dto/order';
import { StockProjection } from '../dto/stock.projection';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-portofolio',
  templateUrl: './portofolio.component.html',
  styleUrls: ['./portofolio.component.css']
})
export class PortofolioComponent implements OnInit, OnDestroy {

  stockUpdates: StockUpdate[] = [];
  myOrders: Order[] = [];
  orderProjections = [];
  private webSocketAPI: WebSocketAPI;
  private accountId;

  constructor(
    private portofolioService: PortofolioService,
    private orderService: OrderService,
    private authService: AuthService) {
    this.webSocketAPI = new WebSocketAPI(this);
  }


  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem("currentUser"));
    console.log(currentUser);
    this.accountId = currentUser['accountId'];
    
    // this.accountId =
      this.connect();
    this.orderService.getOrdersForUser(this.accountId).subscribe(orders => {
      console.log(orders);
      this.myOrders = orders;
      this.manageOrders();
    })
  }

  manageOrders() {
    console.log("Manage orders called");
    this.orderProjections = [];
    this.myOrders.forEach(order => {
      let foundCorelation = this.stockUpdates.find(stkUpdt => order.stockSymbol.toUpperCase() === stkUpdt.ticker.toUpperCase());
      if (foundCorelation) {
        let stockProjection: StockProjection = new StockProjection();
        stockProjection.stockSymbol = order.stockSymbol;
        stockProjection.invested = order.value;
        stockProjection.currentValue = foundCorelation.last;
        stockProjection.profitLoss = order.value * foundCorelation.last - order.price;
        this.orderProjections.push(stockProjection);
      }
    })
    console.log("order projections: ", this.orderProjections);

  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage(message) {
    this.webSocketAPI._send(message);
  }


  handleMessage(message: StockUpdate[]) {
    this.stockUpdates = message;
    console.log(this.stockUpdates);
    this.manageOrders();

  }

}
