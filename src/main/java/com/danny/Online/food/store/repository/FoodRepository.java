package com.danny.Online.food.store.repository;

import com.danny.Online.food.store.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f " + "FROM Food f " + "WHERE lower(f.name) LIKE lower(CONCAT('%', :query, '%'))" + " OR lower(f" +
                   ".foodCategory) LIKE lower(CONCAT('%', :query, '%'))")
    List<Food> searchFood(@Param("query") String query);
}
