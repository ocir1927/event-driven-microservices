package com.costin.disertatie.api.entity;

public class UserAccountDTO {

    private String username;

    private String password;

    private String accountId; //account integration id

    private String name;

    private String email;

    private String description;

    public UserAccountDTO(String username, String password, String accountId, String name, String email, String description) {
        this.username = username;
        this.password = password;
        this.accountId = accountId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
