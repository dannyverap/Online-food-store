package com.danny.Online.food.store.repository;

import com.danny.Online.food.store.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
