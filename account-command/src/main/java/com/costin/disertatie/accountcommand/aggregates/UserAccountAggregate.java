package com.costin.disertatie.accountcommand.aggregates;

import com.costin.disertatie.api.command.CreateUserAccountCommand;
import com.costin.disertatie.api.event.UserAccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class UserAccountAggregate {

    private final Logger LOG = LoggerFactory.getLogger(UserAccountAggregate.class);

    @AggregateIdentifier
    private String username;

    private String password;

    private String accountId; //account integration id

    private String name;

    private String email;

    private String description;

    public UserAccountAggregate() {
    }

    @CommandHandler
    public UserAccountAggregate(CreateUserAccountCommand event) {
        LOG.info("Create user account command");
        AggregateLifecycle.apply(new UserAccountCreatedEvent(event.username, event.password, event.accountId, event.name, event.email, event.description));
    }

    @EventSourcingHandler
    public void on(UserAccountCreatedEvent event) {
        //make assignments
        this.username = event.username;
        this.password = event.password;
        this.accountId = event.accountId;
        this.name = event.name;
        this.email = event.email;
        this.description = event.description;
        LOG.info("User account created for username: " + this.username);
    }
}
