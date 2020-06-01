package com.disertatie.accountprojection.query;

import com.costin.disertatie.api.entity.AccountDTO;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.AccountCreatedEvent;
import com.costin.disertatie.api.event.AccountCreditedEvent;
import com.costin.disertatie.api.event.AccountDebitedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountProjection {

    @Autowired
    private AccountRepository accountRepository;

    public AccountProjection() {
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {

        accountRepository.save(new Account(event.id, event.accountBalance, event.currency,event.owner, Status.CREATED.toString()));
    }

//    @EventHandler
//    public void on(BikeRentedEvent event) {
//        bikeStatusRepository.findById(event.getBikeId()).map(bs -> {
//            bs.setRenter(event.getRenter());
//            return bs;
//        });
//    }

    @EventSourcingHandler
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

//    @QueryHandler(queryName = "findAll")
//    public Iterable<BikeStatus> findAll() {
//        return bikeStatusRepository.findAll();
//    }

    @QueryHandler(queryName = "getAccount")
    public AccountDTO findOne(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return new AccountDTO(account.getAccountId(),account.getAccountBalance(),account.getCurrency(),account.getStatus(),account.getOwner());
    }
}