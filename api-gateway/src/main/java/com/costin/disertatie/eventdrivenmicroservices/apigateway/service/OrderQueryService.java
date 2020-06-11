package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.api.query.GetAllOrdersForAccount;
import com.costin.disertatie.api.query.GetAllOrdersQuery;
import com.costin.disertatie.api.query.GetOrderQuery;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderQueryService {

    private final EventStore eventStore;
    private QueryGateway queryGateway;

    public OrderQueryService(EventStore eventStore, QueryGateway queryGateway) {
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }


    public CompletableFuture<OrderDTO> getOrder(String orderId) {
        return queryGateway.query(new GetOrderQuery(orderId), ResponseTypes.instanceOf(OrderDTO.class));
    }

    public CompletableFuture<List<OrderDTO>> getOrders() {
        return queryGateway.query(new GetAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderDTO.class));
    }

    public CompletableFuture<List<OrderDTO>> getOrdersForAccount(String accountId) {
        return queryGateway.query(new GetAllOrdersForAccount(accountId), ResponseTypes.multipleInstancesOf(OrderDTO.class));
    }
}
