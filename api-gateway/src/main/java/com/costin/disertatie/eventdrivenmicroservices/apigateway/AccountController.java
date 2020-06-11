package com.costin.disertatie.eventdrivenmicroservices.apigateway;


import com.costin.disertatie.api.entity.AccountDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.AccountCreateDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyCreditDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.MoneyDebitDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.AccountCommandService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.AccountQueryService;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/bank-accounts")
@Api(value = "Account Commands", description = "Account Commands Related Endpoints", tags = "Account Commands")
public class AccountController {

    private AccountCommandService accountCommandService;

    private AccountQueryService accountQueryService;

    public AccountController(AccountCommandService accountCommandService, AccountQueryService accountQueryService ) {
        this.accountCommandService = accountCommandService;
        this.accountQueryService = accountQueryService;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        return accountCommandService.createAccount(accountCreateDTO);
    }
    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                          @RequestBody MoneyCreditDTO moneyCreditDTO){
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }
    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                           @RequestBody MoneyDebitDTO moneyDebitDTO){
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.listEventsForAccount(accountNumber);
    }

    @GetMapping
    public CompletableFuture<List<AccountDTO>> getAccounts(){
        return accountQueryService.getAccounts();
    }
    @GetMapping("/accounts/{accountId}")
    public CompletableFuture<AccountDTO> getAccount(@PathVariable(value = "accountId") String accountId){
        return accountQueryService.getAccount(accountId);
    }

}
