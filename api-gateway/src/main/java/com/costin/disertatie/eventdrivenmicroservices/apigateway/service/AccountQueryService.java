package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.entity.AccountDTO;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AccountQueryService {
    private final EventStore eventStore;
    private QueryGateway queryGateway;

    public AccountQueryService(EventStore eventStore, QueryGateway queryGateway) {
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }

    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }

    public CompletableFuture<AccountDTO> getAccount(String accountId) {
        return queryGateway.query("getAccount",accountId, ResponseTypes.instanceOf(AccountDTO.class));
    }

}