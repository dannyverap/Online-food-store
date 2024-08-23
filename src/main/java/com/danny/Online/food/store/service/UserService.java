package com.danny.Online.food.store.service;

import com.danny.Online.food.store.model.User;

public interface UserService {

    public User findUserByToken(String token) throws Exception;
    public User findUserByEmail(String email) throws Exception;

}
