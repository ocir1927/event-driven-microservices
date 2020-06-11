package com.costin.disertatie.stock.connector;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class StockWebsocketClient {

    Session userSession = null;
    private MessageHandler messageHandler;

    public StockWebsocketClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("Handling message: " + message);
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        this.userSession.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public static interface MessageHandler {

        public void handleMessage(String message);
    }


    /*
        try {
            // open websocket
            final StockWebsocketClient clientEndPoint = new StockWebsocketClient(new URI("wss://api.tiingo.com/crypto"));

            // add listener
            clientEndPoint.addMessageHandler(new StockWebsocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            JsonObject jsonObjectAAPL = new JsonObject();
            jsonObjectAAPL.addProperty("eventName","subscribe");
            jsonObjectAAPL.addProperty("authorization","8cf64985b147336926b8560b2e200bb4793fb7e8");
            JsonObject eventData = new JsonObject();
            eventData.addProperty("thresholdLevel","5");
            jsonObjectAAPL.add("eventData",eventData);
            clientEndPoint.sendMessage(jsonObjectAAPL.toString());
//            JsonObject jsonObjectBTC = new JsonObject();
//            jsonObjectBTC.addProperty("type","subscribe");
//            jsonObjectBTC.addProperty("symbol","BTC");
//            clientEndPoint.sendMessage(jsonObjectBTC.toString());

            // wait 5 seconds for messages from websocket
//            Thread.sleep(5000);

//        } catch (InterruptedException ex) {
//            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
*/
}
