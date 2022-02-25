package com.yoyo.service;

public class ItemDetail {

    private String id;
    private String name;
    private double price;

    private ItemDetail() {

    }

    public ItemDetail(String id, String name, double price) {

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }
}
