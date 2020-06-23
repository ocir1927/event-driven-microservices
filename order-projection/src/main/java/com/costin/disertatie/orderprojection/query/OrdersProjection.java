package com.costin.disertatie.orderprojection.query;

import com.costin.disertatie.api.command.OrderPaidEvent;
import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.*;
import com.costin.disertatie.api.query.GetAllOrdersForAccount;
import com.costin.disertatie.api.query.GetAllOrdersQuery;
import com.costin.disertatie.api.query.GetOrderCountForAccount;
import com.costin.disertatie.api.query.GetOrderQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdersProjection {

    private OrderRepository orderRepository;

    public OrdersProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        orderRepository.save(new OrderEntity(event.id, event.accountId, event.value, event.stockId, event.status,event.price));
    }


    @EventHandler
    protected void on(OrderPaidEvent event) {
        orderRepository.findById(event.id).map(order -> {
                    order.setStatus(Status.COMPLETE);
                    return order;
                });
    }

    @EventHandler
    public void on(OrderClosedEvent event){
        orderRepository.findById(event.orderId).map(order->{
            order.setStatus(Status.CLOSED);
            return order;
        });
    }

    @QueryHandler
    public OrderDTO findOne(GetOrderQuery query) {
        OrderEntity order = orderRepository.findById(query.orderId).orElseThrow();
        return new OrderDTO(order.getOrderId(), order.getAccountId(), order.getValue(), order.getStockSymbol(), order.getStatus(),order.getPrice());
    }

    @QueryHandler
    public List<OrderDTO> findAll(GetAllOrdersQuery query) {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderDTO(order.getOrderId(), order.getAccountId(), order.getValue(), order.getStockSymbol(), order.getStatus(),order.getPrice()))
                .collect(Collectors.toList());

    }

    @QueryHandler
    public List<OrderDTO> findAllForAccount(GetAllOrdersForAccount query){
        List<OrderEntity> ordersForId = orderRepository.findOrderEntitiesByAccountNotClosed(query.getAccountId());
        return ordersForId.stream()
                .map(order -> new OrderDTO(order.getOrderId(), order.getAccountId(), order.getValue(), order.getStockSymbol(), order.getStatus(),order.getPrice()))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public Integer findAllForAccount(GetOrderCountForAccount query){
        return orderRepository.countAllByAccountIdNotClosed(query.accountId);
    }
}