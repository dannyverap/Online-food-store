package com.danny.Online.food.store.request;

import com.danny.Online.food.store.model.Address;
import com.danny.Online.food.store.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
