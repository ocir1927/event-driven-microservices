package com.costin.disertatie.api.event;

public class UserAccountUpdatedEvent {

    public String username;
    public String name;
    public String email;
    public String description;

    public UserAccountUpdatedEvent(String username, String name, String email, String description) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
