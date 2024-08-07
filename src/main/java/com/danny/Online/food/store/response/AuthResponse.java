package com.danny.Online.food.store.response;

import com.danny.Online.food.store.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
