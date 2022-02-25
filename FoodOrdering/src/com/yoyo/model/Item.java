package com.yoyo.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private String id;
    private String name;
    private String restaurantId;
    private Double price;
    private String description;

    private Item() {

    }

    public Item(String name, String restaurantId, Double price) {

        this.id = String.format("%03d", idGenerator.getAndIncrement());
        this.name = name;
        this.restaurantId = restaurantId;
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public String getRestaurantId() {

        return restaurantId;
    }

    public Double getPrice() {

        return price;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getId() {

        return id;
    }
}
