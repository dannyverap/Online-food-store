package com.danny.Online.food.store.service;

import com.danny.Online.food.store.config.JwtProvider;
import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @Transactional
    public User findUserByToken(String token) throws Exception {
        String email = jwtProvider.getEmailFromJWT(token);

        User user = findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        //TODO: Alternative to not force favorites and addresses to be loaded
        user.getFavorites().size();
        user.getAddresses().size();
        return user;
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}
