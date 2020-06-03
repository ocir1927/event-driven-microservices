package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.command.CreateNewOrderCommand;
import com.costin.disertatie.api.entity.AccountDTO;
import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.api.query.GetAllAccountsQuery;
import com.costin.disertatie.api.query.GetAllOrdersQuery;
import com.costin.disertatie.api.query.GetOrderQuery;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.CreateOrderDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
}
