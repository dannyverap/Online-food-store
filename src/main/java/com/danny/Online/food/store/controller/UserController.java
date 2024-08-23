package com.danny.Online.food.store.controller;

import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByToken(token);
        return ResponseEntity.ok(user);
    }
}
