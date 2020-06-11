import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Component, OnInit } from '@angular/core';
// import { AppComponent } from './app.component';

export class WebSocketAPI {
    webSocketEndPoint: string = 'http://localhost:8095/stockws';
    topic: string = "/topic/stock-update";
    stompClient: any;
    appComponent: OnInit;
    constructor(appComponent: OnInit){
        this.appComponent = appComponent;
    }


    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        this.stompClient.debug = null;
        const _this = this;
        _this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
                _this.onMessageReceived(sdkEvent);
            });
            _this._send("gimme stocks");
            //_this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

 /**
  * Send message to sever via web socket
  * @param {*} message 
  */
    _send(message) {
        console.log("calling api to give stocks");
        this.stompClient.send("/app/getLatestStockValues", {}, message);
    }

    onMessageReceived(message) {
        // console.log("Message Recieved from Server :: " + message);
        this.appComponent['handleMessage'](JSON.parse(message.body));
    }

}