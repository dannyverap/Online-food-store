package com.danny.Online.food.store.service;

import com.danny.Online.food.store.model.Food;
import com.danny.Online.food.store.model.FoodCategory;
import com.danny.Online.food.store.model.Restaurant;
import com.danny.Online.food.store.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest request,
                           FoodCategory foodCategory,
                           Restaurant restaurant) throws Exception;

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getFoodsByRestaurantId(Long restaurantId,
                                             boolean isVegetarian,
                                             boolean isNonVeg,
                                             boolean isSeasonal,
                                             String foodCategory) throws Exception;

    public List<Food> searchFood(String query);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateFoodStatus(Long foodId) throws Exception;
}
