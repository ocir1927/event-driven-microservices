package com.costin.disertatie.stock.connector;

import com.costin.disertatie.stock.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class StockWebsocketEndpoint {

    private SimpMessagingTemplate simpMessagingTemplate;

    public StockWebsocketEndpoint(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/stock-update")
    @SendTo("/topic/stock-update")
    public String broadcastStocks(@Payload String message) {
        System.out.println(message);
        return message;
    }

    @SendTo("/topic/stock-update")
    @MessageMapping("/getLatestStockValues")
    public String getLatestStockValues(@Payload String clientMessage){
        System.out.println("Message from client: " + clientMessage);
//        System.out.println("The cached prices: " + TiingoStockPricePooling.getCachedRespone());
        return TiingoStockPricePooling.getCachedRespone();
    }


}
