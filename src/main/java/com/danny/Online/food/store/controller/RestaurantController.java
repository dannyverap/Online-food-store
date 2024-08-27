package com.danny.Online.food.store.controller;

import com.danny.Online.food.store.dto.RestaurantDto;
import com.danny.Online.food.store.model.Restaurant;
import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.service.RestaurantService;
import com.danny.Online.food.store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final UserService userService;
    private final RestaurantService restaurantService;

    public RestaurantController(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam("query") String query,
                                                              @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByToken(token);
        List<Restaurant> restaurants = restaurantService.searchRestaurants(query);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("restaurantId") Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/toggle-favorite")
    public ResponseEntity<RestaurantDto> addToFavorite(@RequestHeader("Authorization") String token,
                                                       @PathVariable("restaurantId") Long restaurantId) throws Exception {
        User user = userService.findUserByToken(token);
        RestaurantDto restaurant = restaurantService.toggleFavoriteStatus(restaurantId, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
