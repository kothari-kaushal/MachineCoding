package com.yoyo.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private String id;
    private List<OrderItem> items;
    private String status;

    public Order() {

    }

    public Order(List<OrderItem> items) {

        this.id = String.format("%03d", idGenerator.incrementAndGet());
        this.items = items;
    }

    public String getId() {

        return id;
    }

    public List<OrderItem> getItems() {

        return items;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    @Override
    public String toString() {

        return "Order{" + "id='" + id + '\'' + ", items=" + items + '\'' + '}';
    }
}
