package com.costin.disertatie.accountprojection.query;

import com.costin.disertatie.api.entity.AccountDTO;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.AccountCreatedEvent;
import com.costin.disertatie.api.event.AccountCreditedEvent;
import com.costin.disertatie.api.event.AccountDebitedEvent;
import com.costin.disertatie.api.query.GetAllAccountsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountProjection {

    private AccountRepository accountRepository;

    public AccountProjection(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        accountRepository.save(new Account(event.id, event.accountBalance, event.currency, Status.CREATED.toString()));
    }


    @EventHandler
    protected void on(AccountCreditedEvent moneyCreditedEvent){
        Optional<Account> account= accountRepository.findById(moneyCreditedEvent.id);
        account.ifPresent(value -> value.setAccountBalance(value.getAccountBalance() + moneyCreditedEvent.creditAmount));
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        accountRepository.findById(event.id).map(account -> {
            account.setAccountBalance(account.getAccountBalance() - event.debitAmount);
            return account;
        });
    }

    @QueryHandler(queryName = "getAccount")
    public AccountDTO findOne(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return new AccountDTO(account.getAccountId(),account.getAccountBalance(),account.getCurrency(),account.getStatus());
    }

    @QueryHandler
    public List<AccountDTO> findAll(GetAllAccountsQuery query) {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map( account -> new AccountDTO(account.getAccountId(),account.getAccountBalance(),account.getCurrency(),account.getStatus()))
                .collect(Collectors.toList());

    }
}