package com.danny.Online.food.store.request;

import com.danny.Online.food.store.model.FoodCategory;
import com.danny.Online.food.store.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;
    private FoodCategory category;
    private List<String> images;
    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;
}
