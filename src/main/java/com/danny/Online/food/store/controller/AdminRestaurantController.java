package com.danny.Online.food.store.controller;

import com.danny.Online.food.store.model.Restaurant;
import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.request.CreateRestaurantRequest;
import com.danny.Online.food.store.request.UpdateRestaurantRequest;
import com.danny.Online.food.store.response.MessageResponse;
import com.danny.Online.food.store.service.RestaurantService;
import com.danny.Online.food.store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/restaurant")
public class AdminRestaurantController {

    private final UserService userService;
    private final RestaurantService restaurantService;

    public AdminRestaurantController(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByToken(token);
        Restaurant restaurant = restaurantService.createRestaurant(request, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody UpdateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String token,
                                                       @PathVariable("restaurantId") Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurant(request, restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String token,
                                                            @PathVariable("restaurantId") Long restaurantId) throws Exception {
        User user = userService.findUserByToken(token);
        restaurantService.deleteRestaurant(restaurantId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Deleted");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String token,
                                                             @PathVariable("restaurantId") Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByToken(token);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
