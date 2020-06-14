package com.costin.disertatie.ordercommand;

import com.costin.disertatie.api.command.DebitAccountCommand;
import com.costin.disertatie.api.command.MarkOrderPaidCommand;
import com.costin.disertatie.api.command.OrderPaidEvent;
import com.costin.disertatie.api.event.AccountDebitedEvent;
import com.costin.disertatie.api.event.OrderCreatedEvent;
import com.costin.disertatie.api.event.OrderPendingEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Saga
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private EventGateway eventGateway;

    private boolean paid = false;
    private String orderId;

    private final Logger LOG = LoggerFactory.getLogger(OrderManagementSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "id", keyName ="orderId" )
    public void handle(OrderCreatedEvent event) {
        // client generated identifiers
        this.orderId = event.id;
        String transactionId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("transactionId", transactionId);
        // send the commands
        LOG.debug("Sending debit money command");
        commandGateway.send(new DebitAccountCommand(event.accountId,event.price, transactionId, "USD"));
    }


    @SagaEventHandler(associationProperty = "transactionId", keyName = "transactionId")
    public void handle(AccountDebitedEvent event) {
        paid = true;
        LOG.info("Account paid, mark order as complete");
        commandGateway.send(new MarkOrderPaidCommand(this.orderId));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void handle(OrderPaidEvent orderPaidEvent) {
        LOG.info("Order complete, Saga ends");
    }
}
