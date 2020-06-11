package com.costin.disertatie.eventdrivenmicroservices.apigateway;

import com.costin.disertatie.api.entity.UserAccountDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.UserAccountCreateDTO;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.UserLogin;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserAccountCommandService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserAccountQueryService;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Api(value = "User Login and Sign-Up", description = "Login/Sign-up", tags = "Login/Sign-up")
public class AuthenticationController {

    @Autowired
    UserAccountCommandService userAccountCommandService;

    @Autowired
    UserAccountQueryService userAccountQueryService;

    @Autowired
    private UserService userService;


    @PostMapping("/sign-up")
    public CompletableFuture<String> createNewUser(@RequestBody UserAccountCreateDTO accountCreateDTO){
        System.out.println("Now sendind create account command :" + accountCreateDTO.toString());
        return userAccountCommandService.createAccount(accountCreateDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccountDTO> login(@RequestBody UserLogin userLogin){
        UserAccountDTO userAccountDTO = null;
        try {
             userAccountDTO = userAccountQueryService.login(userLogin);
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(userAccountDTO == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        return new ResponseEntity<UserAccountDTO>(userAccountDTO,HttpStatus.OK);
    }

}
