package com.costin.disertatie.eventdrivenmicroservices.apigateway.service;

import com.costin.disertatie.api.entity.UserAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class UserService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserAccountQueryService userAccountQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountDTO applicationUser = null;
        try {
            applicationUser = userAccountQueryService.getUserAccount(username).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        logger.debug("loadUserByUsername --> applicationUser = {}", applicationUser);

        if (applicationUser == null) {
            logger.warn("User with username [{}] was not found", username);
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(),new ArrayList<>());
    }


}
