package com.costin.disertatie.api.command;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateUserProfileCommand {

    @TargetAggregateIdentifier
    public String username;

    public String name;

    public String email;

    public String description;

    public UpdateUserProfileCommand(String username, String name, String email, String description) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
