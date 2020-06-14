package com.costin.disertatie.ordercommand.aggregate;

import com.costin.disertatie.api.command.*;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private double value;

    private String stockSymbol;

    private String accountId;

    private Status status;

    private double price;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateNewOrderCommand createNewOrderCommand){
//        if(createNewOrderCommand.value <50){
//            throw new IllegalArgumentException("Value must be >50");
//        }
        apply(new OrderCreatedEvent(createNewOrderCommand.id, createNewOrderCommand.accountId, createNewOrderCommand.stockId,createNewOrderCommand.value, Status.PENDING, createNewOrderCommand.price));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        this.orderId = orderCreatedEvent.id;
        this.accountId = orderCreatedEvent.accountId;
        this.stockSymbol = orderCreatedEvent.stockId;
        this.value = orderCreatedEvent.value;
        this.status = orderCreatedEvent.status;
        this.price = orderCreatedEvent.price;
    }

    @CommandHandler
    public void handle(MarkOrderPaidCommand markOrderPaidCommand){
        apply(new OrderPaidEvent(markOrderPaidCommand.id));
    }

    @EventSourcingHandler
    public void on(OrderPaidEvent orderPaidEvent){
        this.status = Status.COMPLETE;
    }
}
