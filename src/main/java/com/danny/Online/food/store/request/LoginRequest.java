package com.danny.Online.food.store.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
