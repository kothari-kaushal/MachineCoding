package com.yoyo.service;

import com.yoyo.model.Restaurant;

import java.util.List;

public interface IRestaurantService {

    void addRestaurant(String id, List<ItemDetail> menuItems, int capacity);

    boolean canTakeOrder(String id);

    void addItem(String id, String item, String price);

    Restaurant getById(String id);

    void replenish(String id);

}
