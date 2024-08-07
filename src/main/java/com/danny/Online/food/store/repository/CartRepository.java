package com.danny.Online.food.store.repository;

import com.danny.Online.food.store.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
