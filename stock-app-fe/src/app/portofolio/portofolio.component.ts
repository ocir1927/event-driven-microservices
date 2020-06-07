import { Component, OnInit, OnDestroy } from '@angular/core';
import { PortofolioService } from './portofolio.service';
import { WebSocketAPI } from './websocket.api';
import { StockUpdate } from '../dto/stock.update';

@Component({
  selector: 'app-portofolio',
  templateUrl: './portofolio.component.html',
  styleUrls: ['./portofolio.component.css']
})
export class PortofolioComponent implements OnInit, OnDestroy {

  ownedStocks: StockUpdate[] = [];
  private webSocketAPI: WebSocketAPI;

  constructor(private portofolioService: PortofolioService) {
    this.webSocketAPI = new WebSocketAPI(this);
  }


  ngOnInit() {
    this.connect();
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(message){
    this.webSocketAPI._send(message);
  }


  handleMessage(message: StockUpdate[]){
    this.ownedStocks = message;
    console.log(this.ownedStocks);
    
  }

}
