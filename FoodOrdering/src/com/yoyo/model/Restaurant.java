package com.yoyo.model;

public class Restaurant {

    private String id;
    private int capacity;

    private Restaurant() {

    }

    public Restaurant(String id, int capacity) {

        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {

        return id;
    }

    public int getCapacity() {

        return capacity;
    }

    public void setCapacity(int capacity) {

        this.capacity = capacity;
    }
}
