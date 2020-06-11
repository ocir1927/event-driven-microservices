package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.command.CreateAccountCommand;
import com.costin.disertatie.api.command.CreateUserAccountCommand;
import com.costin.disertatie.api.command.CreditAccountCommand;
import com.costin.disertatie.api.command.DebitAccountCommand;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.AccountCreateDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyCreditDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyDebitDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.UserAccountCreateDTO;
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

//    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
//        return commandGateway.send(new CreditAccountCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
//    }
//
//    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
//        return commandGateway.send(new DebitAccountCommand(accountNumber, moneyDebitDTO.getDebitAmount(),UUID.randomUUID().toString(), moneyDebitDTO.getCurrency()));
//    }
}
