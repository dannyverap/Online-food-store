package com.danny.Online.food.store.service;

import com.danny.Online.food.store.dto.RestaurantDto;
import com.danny.Online.food.store.model.Restaurant;
import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.request.CreateRestaurantRequest;
import com.danny.Online.food.store.request.UpdateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) throws Exception;

    public Restaurant updateRestaurant(UpdateRestaurantRequest updateRestaurant, Long restaurantId) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String query);

    public Restaurant getRestaurantById(Long restaurantId) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto toggleFavoriteStatus(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
