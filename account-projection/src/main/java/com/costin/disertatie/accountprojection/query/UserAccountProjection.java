package com.costin.disertatie.accountprojection.query;

import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.api.event.UserAccountCreatedEvent;
import com.costin.disertatie.api.query.GetUserAccountByUsernameQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountProjection {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @EventHandler
    public void on(UserAccountCreatedEvent event) {
        System.out.println("User account projection: UserAccountCreatedEvent");
        userAccountRepository.save(new UserAccount(event.username, event.password, event.accountId, event.name,event.email,event.description));
    }


//    @EventHandler
//    protected void on(AccountCreditedEvent moneyCreditedEvent){
//        Optional<Account> account= accountRepository.findById(moneyCreditedEvent.id);
//        account.ifPresent(value -> value.setAccountBalance(value.getAccountBalance() + moneyCreditedEvent.creditAmount));
//    }


    @QueryHandler
    public UserAccountDTO findOne(GetUserAccountByUsernameQuery query) {
        UserAccount account = userAccountRepository.findById(query.username).orElseThrow();
        return new UserAccountDTO(account.getUsername(),account.getPassword(),account.getAccountId(),account.getName(),account.getEmail(),account.getDescription());
    }

//    @QueryHandler
//    public List<AccountDTO> findAll(GetAllAccountsQuery query) {
//        List<Account> accounts = accountRepository.findAll();
//        return accounts.stream()
//                .map( account -> new AccountDTO(account.getAccountId(),account.getAccountBalance(),account.getCurrency(),account.getStatus()))
//                .collect(Collectors.toList());
//
//    }
}
