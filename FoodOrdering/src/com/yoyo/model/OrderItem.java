package com.yoyo.model;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderItem {

    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private String id;
    private String itemId;
    private String restaurantId;
    private String status;

    private OrderItem() {

    }

    public OrderItem(String itemId, String restaurantId) {

        this.id = String.format("%03d", idGenerator.getAndIncrement());
        this.itemId = itemId;
        this.restaurantId = restaurantId;
    }

    public String getId() {

        return id;
    }

    public String getItemId() {

        return itemId;
    }

    public String getRestaurantId() {

        return restaurantId;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    @Override
    public String toString() {

        return "OrderItem{" + "id='" + id + '\'' + ", itemId='" + itemId + '\'' + ", restaurantId='" + restaurantId
                + '\'' + '}';
    }
}
