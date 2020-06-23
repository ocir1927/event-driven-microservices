package com.costin.disertatie.eventdrivenmicroservices.apigateway.dto;

public class UserProfileDTO {

    private String username;

    private String name;

    private String email;

    private String description;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String username, String name, String email, String description) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
