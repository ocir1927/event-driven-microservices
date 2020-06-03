package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.command.CreateNewOrderCommand;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.CreateOrderDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderCommandService {

    private final CommandGateway commandGateway;

    public OrderCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createOrder(CreateOrderDTO createOrderDTO) {
        return this.commandGateway.send(new CreateNewOrderCommand(UUID.randomUUID().toString(),createOrderDTO.getAccountId(),createOrderDTO.getStockId(),createOrderDTO.getValue()));
    }
}
