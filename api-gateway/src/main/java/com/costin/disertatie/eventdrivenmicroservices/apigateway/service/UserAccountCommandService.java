package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.command.*;
import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class UserAccountCommandService {

    private final CommandGateway commandGateway;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccountCommandService(CommandGateway commandGateway) {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(UserAccountCreateDTO accountCreateDTO) {
        String encryptedPassword = bCryptPasswordEncoder.encode(accountCreateDTO.getPassword());

        return commandGateway.send(new CreateUserAccountCommand(accountCreateDTO.getUsername(), encryptedPassword, UUID.randomUUID().toString(), accountCreateDTO.getName(), accountCreateDTO.getEmail(), accountCreateDTO.getDescription()));
    }

    public CompletableFuture<UserAccountDTO> updateProfile(UserProfileDTO userProfile) {
        return commandGateway.send(new UpdateUserProfileCommand(userProfile.getUsername(),userProfile.getName(),userProfile.getEmail(),userProfile.getDescription()));
    }
}
