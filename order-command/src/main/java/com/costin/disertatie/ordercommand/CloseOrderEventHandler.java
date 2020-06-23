package com.costin.disertatie.ordercommand;

import com.costin.disertatie.api.command.CreditAccountCommand;
import com.costin.disertatie.api.event.OrderClosedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CloseOrderEventHandler {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private EventGateway eventGateway;

    @EventHandler
    public void on(OrderClosedEvent event){
        System.out.println("order closed evend handler");
        commandGateway.send(new CreditAccountCommand(event.accountId,event.finalValue,"USD"));
    }
}
