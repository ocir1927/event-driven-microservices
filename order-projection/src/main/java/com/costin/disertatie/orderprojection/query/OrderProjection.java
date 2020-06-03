package com.costin.disertatie.orderprojection.query;

import com.costin.disertatie.api.command.OrderPaidEvent;
import com.costin.disertatie.api.entity.AccountDTO;
import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.*;
import com.costin.disertatie.api.query.GetAllAccountsQuery;
import com.costin.disertatie.api.query.GetAllOrdersQuery;
import com.costin.disertatie.api.query.GetOrderQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderProjection {

    private OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        orderRepository.save(new Order(event.id, event.accountId, event.value, event.stockId, event.status));
    }


    @EventHandler
    protected void on(OrderPaidEvent event) {
        orderRepository.findById(event.id).map(order -> {
                    order.setStatus(Status.COMPLETE);
                    return order;
                });
    }
//
//    @EventHandler
//    public void on(AccountDebitedEvent event) {
//        orderRepository.findById(event.id).map(account -> {
//            account.setAccountBalance(account.getAccountBalance() - event.debitAmount);
//            return account;
//        });
//    }

    @QueryHandler
    public OrderDTO findOne(GetOrderQuery query) {
        Order order = orderRepository.findById(query.orderId).orElseThrow();
        return new OrderDTO(order.getOrderId(), order.getAccountId(), order.getValue(), order.getStockSymbol(), order.getStatus());
    }

    @QueryHandler
    public List<OrderDTO> findAll(GetAllOrdersQuery query) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderDTO(order.getOrderId(), order.getAccountId(), order.getValue(), order.getStockSymbol(), order.getStatus()))
                .collect(Collectors.toList());

    }
}