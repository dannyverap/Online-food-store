package com.danny.Online.food.store.service;

import com.danny.Online.food.store.model.Food;
import com.danny.Online.food.store.model.FoodCategory;
import com.danny.Online.food.store.model.Restaurant;
import com.danny.Online.food.store.repository.FoodRepository;
import com.danny.Online.food.store.request.CreateFoodRequest;

import java.util.List;
import java.util.Optional;

public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;


    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food createFood(CreateFoodRequest request,
                           FoodCategory foodCategory,
                           Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(foodCategory);
        food.setRestaurant(restaurant);
        food.setDescription(request.getDescription());
        food.setImages(request.getImages());
        food.setName(request.getName());
        food.setPrice(request.getPrice());
        food.setIngredientsItem(request.getIngredients());
        food.setVegetarian(request.isVegetarian());
        food.setSeasonal(request.isSeasonal());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.delete(food);
    }

    @Override
    public List<Food> getFoodsByRestaurantId(Long restaurantId,
                                             boolean isVegetarian,
                                             boolean isNonVeg,
                                             boolean isSeasonal,
                                             String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        return foods.stream()
                    .filter(food -> !isVegetarian || food.isVegetarian())
                    .filter(food -> !isNonVeg || !food.isVegetarian())
                    .filter(food -> !isSeasonal || food.isSeasonal())
                    .filter(food -> foodCategory == null || foodCategory.isEmpty() ||
                                            food.getFoodCategory().getName().equals(foodCategory))
                    .toList();
    }

    @Override
    public List<Food> searchFood(String query) {
        return foodRepository.searchFood(query);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isEmpty()) {
            throw new Exception("Food not found");
        }
        return food.get();
    }

    @Override
    public Food updateFoodStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
