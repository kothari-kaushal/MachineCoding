package com.yoyo.service;

import com.yoyo.dao.Repo;
import com.yoyo.exception.InvalidInputException;
import com.yoyo.model.Item;
import com.yoyo.model.Restaurant;

import java.util.List;

public class RestaurantService implements IRestaurantService {

    private final Repo<Restaurant> restRepo;
    private final Repo<Item> itemRepo;

    public RestaurantService(Repo<Restaurant> restRepo, Repo<Item> itemRepo) {

        this.restRepo = restRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public void addRestaurant(String restaurantId, List<ItemDetail> itemDetails, int capacity) {

        Restaurant restaurant = this.restRepo.getById(restaurantId);
        if (restaurant != null) {
            throw new InvalidInputException(String.format("restaurant %s already exists in the system", restaurantId));
        }
        if (capacity < 1) {
            throw new InvalidInputException("capacity must be at the least 1");
        }

        Restaurant newRestaurant = new Restaurant(restaurantId, capacity);
        this.restRepo.save(restaurantId, newRestaurant);

        itemDetails.forEach(itemDetail -> {
            String itemId = itemDetail.getName();

            Item item = new Item(itemId, restaurantId, itemDetail.getPrice());
            this.itemRepo.save(item.getId(), item);
        });
    }

    @Override
    public boolean canTakeOrder(String id) {

        Restaurant restaurant = this.restRepo.getById(id);
        if (restaurant == null) {
            throw new InvalidInputException(String.format("restaurant %s not found", id));
        }
        synchronized (id) {
            restaurant = this.restRepo.getById(id);
            if (restaurant.getCapacity() <= 0) {
                return false;
            }
            restaurant.setCapacity(restaurant.getCapacity() - 1);
            this.restRepo.save(id, restaurant);
        }
        return true;
    }

    @Override
    public void replenish(String id) {

        Restaurant restaurant = this.restRepo.getById(id);
        if (restaurant == null) {
            throw new InvalidInputException(String.format("restaurant %s not found", id));
        }
        synchronized (id) {
            restaurant = this.restRepo.getById(id);
            restaurant.setCapacity(restaurant.getCapacity() + 1);
            this.restRepo.save(id, restaurant);
        }
    }

    @Override
    public void addItem(String id, String item, String price) {

    }

    @Override
    public Restaurant getById(String id) {

        return this.restRepo.getById(id);
    }
}
