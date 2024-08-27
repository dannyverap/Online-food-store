package com.danny.Online.food.store.controller;

import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.request.LoginRequest;
import com.danny.Online.food.store.response.AuthResponse;
import com.danny.Online.food.store.service.AuthUserService;
import com.danny.Online.food.store.service.CustomerUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthUserService authUserService;
    private final CustomerUserDetailsService customerUserDetailsService;

    public AuthController(AuthUserService authUserService, CustomerUserDetailsService customerUserDetailsService) {
        this.authUserService = authUserService;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    //TODO: SET AUTHORITIES ON TOKEN WHEN USER SIGN UP
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        AuthResponse authResponse = authUserService.SignUp(user);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest user) throws Exception {
        UserDetails authentication = customerUserDetailsService.loadUserByUsername(user.getEmail());
        AuthResponse authResponse = authUserService.SignIn(authentication, user.getPassword());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
