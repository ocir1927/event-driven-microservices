package com.costin.disertatie.accountprojection.query;

import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.api.event.UserAccountCreatedEvent;
import com.costin.disertatie.api.event.UserAccountUpdatedEvent;
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
        userAccountRepository.save(new UserAccount(event.username, event.password, event.accountId, event.name, event.email, event.description));
    }


    @EventHandler
    public void on(UserAccountUpdatedEvent event) {
        userAccountRepository.findById(event.username).map(userAccount -> {
            userAccount.setDescription(event.description);
            userAccount.setEmail(event.email);
            userAccount.setName(event.name);
            return userAccount;
        });
    }

    @QueryHandler
    public UserAccountDTO findOne(GetUserAccountByUsernameQuery query) {
        UserAccount account = userAccountRepository.findById(query.username).orElseThrow();
        return new UserAccountDTO(account.getUsername(), account.getPassword(), account.getAccountId(), account.getName(), account.getEmail(), account.getDescription());
    }

}
