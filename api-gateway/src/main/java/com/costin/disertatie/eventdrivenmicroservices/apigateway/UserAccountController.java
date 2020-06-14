package com.costin.disertatie.eventdrivenmicroservices.apigateway;

import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserAccountCommandService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserAccountQueryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "User Account Commands and Queries", description = "User account management", tags = "User Account Controller")
public class UserAccountController {

    @Autowired
    UserAccountCommandService userAccountCommandService;

    @Autowired
    UserAccountQueryService userAccountQueryService;

    @GetMapping("/{username}")
    public CompletableFuture<UserAccountDTO> getOrder(@PathVariable(value = "username") String username){
        return userAccountQueryService.getUserAccount(username);
    }
}
