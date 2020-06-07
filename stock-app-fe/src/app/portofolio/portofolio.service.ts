import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PortofolioService {

  private serverUrl = 'http://localhost:8080/stockws'
  private title = 'WebSockets chat';
  private stompClient;
  stockList :Observable<any> = new Observable();

  constructor(private http: Http) {
    // this.initializeWebSocketConnection();
    // 
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let _this = this;
    this.stompClient.connect({}, frame => {
      _this.stompClient.subscribe("/topic/stock-update", (message) => {
        // if (message.body) {
          // console.log("De aici: {}", message);
          this.stockList = message;
        // }
      },err=> console.log(err));
    });
  }

  sendMessage(message) {

    this.stompClient.send("/app/stock-update", {}, message);
    // $('#input').val('');
  }

}
