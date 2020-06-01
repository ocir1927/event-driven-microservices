package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;


import com.costin.disertatie.api.command.CreateAccountCommand;
import com.costin.disertatie.api.command.CreditAccountCommand;
import com.costin.disertatie.api.command.DebitAccountCommand;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.AccountCreateDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyCreditDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandService {
    private final CommandGateway commandGateway;
    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency(),accountCreateDTO.getOwner()));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditAccountCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitAccountCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}