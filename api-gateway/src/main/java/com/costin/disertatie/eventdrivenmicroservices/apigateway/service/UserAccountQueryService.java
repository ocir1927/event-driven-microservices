package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.api.query.GetUserAccountByUsernameQuery;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.UserLogin;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserAccountQueryService {

    private final EventStore eventStore;
    private QueryGateway queryGateway;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccountQueryService(EventStore eventStore, QueryGateway queryGateway) {
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    public CompletableFuture<UserAccountDTO> getUserAccount(String username) {
        return queryGateway.query(new GetUserAccountByUsernameQuery(username), ResponseTypes.instanceOf(UserAccountDTO.class));
    }

    public UserAccountDTO login(UserLogin userLogin) throws ExecutionException, InterruptedException, IllegalArgumentException {
        UserAccountDTO userAccountDTO = queryGateway.query(new GetUserAccountByUsernameQuery(userLogin.getUsername()), ResponseTypes.instanceOf(UserAccountDTO.class)).get();
        boolean passwordCorrect = bCryptPasswordEncoder.matches(userLogin.getPassword(),userAccountDTO.getPassword());
        if (passwordCorrect)
            return userAccountDTO;
        else
            throw new IllegalArgumentException("wrong username or password");
    }

}
