package com.yoyo.service;

import com.yoyo.model.Item;

import java.util.List;

public interface SelectionStrategy {

    List<String> getRestaurants(List<Item> items);

}
