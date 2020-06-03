package com.costin.disertatie.ordercommand;

import com.costin.disertatie.api.command.CreateNewOrderCommand;
import com.costin.disertatie.api.command.CreateOrderCommand;
import com.costin.disertatie.api.command.DebitAccountCommand;
import com.costin.disertatie.api.event.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrderCommandHandler {
    @Autowired
    private CommandGateway commandGateway;

//    @CommandHandler
//    public void handle(CreateNewOrderCommand createNewOrderCommand){
//        if(createNewOrderCommand.value < 50) {
//            throw new IllegalArgumentException("Value must be over 50");
//        }
//
//        commandGateway.send(new DebitAccountCommand(createNewOrderCommand.accountId, createNewOrderCommand.value, transactionId, "EUR"));
//    }
//
//    @EventHandler
//    public void on(AccountDebitedEvent accountDebitedEvent) {
//        commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(),accountDebitedEvent.id,"IBM"));
//    }




}
