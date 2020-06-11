package com.costin.disertatie.accountcommand;

import com.costin.disertatie.api.command.CreateAccountCommand;
import com.costin.disertatie.api.event.AccountCreatedEvent;
import com.costin.disertatie.api.event.UserAccountCreatedEvent;
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
public class UserAccountSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private EventGateway eventGateway;

    private boolean paid = false;
    private String username;

    private final Logger LOG = LoggerFactory.getLogger(UserAccountSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "username")
    public void handle(UserAccountCreatedEvent event) {
        this.username = event.username;
        String accountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("accountId", event.accountId);
        // send the commands
        LOG.info("Sending create account command");
        commandGateway.send(new CreateAccountCommand(event.accountId,0,  "USD"));

    }

    @SagaEventHandler(associationProperty = "id", keyName = "accountId")
    public void handle(AccountCreatedEvent event) {
        LOG.info("Account created for username: " + username + "with account id: " + event.id);
        SagaLifecycle.end();
    }

}