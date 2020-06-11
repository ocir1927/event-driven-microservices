package com.costin.disertatie.api.event;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UserAccountCreatedEvent{
    public String username;

    public String password;

    public String accountId; //account integration id

    public String name;

    public String email;

    public String description;

    public UserAccountCreatedEvent(String username, String password, String accountId, String name, String email, String description) {
        this.username = username;
        this.password = password;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.description = description;
    }

}
