package com.danny.Online.food.store.service;

import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserService {
    AuthResponse SignUp(User user) throws Exception;
    AuthResponse SignIn(UserDetails userDetails, String password) throws Exception;
}
