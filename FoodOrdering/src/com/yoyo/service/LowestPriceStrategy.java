package com.yoyo.service;

import com.yoyo.model.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LowestPriceStrategy implements SelectionStrategy {

    @Override
    public List<String> getRestaurants(List<Item> items) {

        items.sort(Comparator.comparing(Item::getPrice));
        return items.stream().map(Item::getRestaurantId).collect(Collectors.toList());
    }
}
