package com.costin.disertatie.eventdrivenmicroservices.apigateway;

import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.CreateOrderDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.OrderCommandService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.OrderQueryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/orders")
@Api(value = "Order Controller", description = "Order query and command related rndpoints", tags = "Orders controller")
public class OrderController {

    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;


    public OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping(value = "new-order")
    public CompletableFuture<String> createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        return orderCommandService.createOrder(createOrderDTO);
    }

    @GetMapping
    public CompletableFuture<List<OrderDTO>> getOrders(){
        return orderQueryService.getOrders();
    }

    @GetMapping("/{orderId}")
    public CompletableFuture<OrderDTO> getOrder(@PathVariable(value = "orderId") String orderId){
        return orderQueryService.getOrder(orderId);
    }

    @GetMapping("/account/{accountId}")
    public CompletableFuture<List<OrderDTO>> getOrdersForAccoubt(@PathVariable(value = "accountId") String accountId) {
        return orderQueryService.getOrdersForAccount(accountId);
    }

}
