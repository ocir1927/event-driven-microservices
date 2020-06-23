package com.costin.disertatie.eventdrivenmicroservices.apigateway;

import com.costin.disertatie.api.entity.OrderDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.CloseOrderDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.CreateOrderDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.OrderCommandService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.OrderQueryService;
import io.swagger.annotations.Api;
import org.axonframework.axonserver.connector.command.AxonServerRemoteCommandHandlingException;
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
        try {
            return orderCommandService.createOrder(createOrderDTO);
        }catch (AxonServerRemoteCommandHandlingException ex){
            CompletableFuture<String> err = new CompletableFuture<String>();
            err.obtrudeValue(ex.toString());
            return err;

        }
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

    @GetMapping("/account/{accountId}/order-count")
    public CompletableFuture<Integer> getOrdersCountForAccount(@PathVariable(value = "accountId") String accountId) {
        return orderQueryService.getOrderCountForAccount(accountId);
    }


    @PutMapping("/close/{orderId}")
    public CompletableFuture<Boolean> closeOrder(@PathVariable(value = "orderId") String orderId, @RequestBody CloseOrderDTO closeOrderDTO){
        return orderCommandService.closeOrder(orderId, closeOrderDTO);
    }



}
