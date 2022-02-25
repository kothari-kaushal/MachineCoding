package com.yoyo;

import com.yoyo.dao.Repo;
import com.yoyo.dao.inmemory.InMemoryRepo;
import com.yoyo.model.Item;
import com.yoyo.model.Order;
import com.yoyo.model.OrderItem;
import com.yoyo.model.Restaurant;
import com.yoyo.service.ItemDetail;
import com.yoyo.service.OrderService;
import com.yoyo.service.RestaurantService;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        Repo<Restaurant> restRepo = new InMemoryRepo<>();
        Repo<Order> orderRepo = new InMemoryRepo<>();
        Repo<OrderItem> orderItemRepo = new InMemoryRepo<>();
        Repo<Item> itemRepo = new InMemoryRepo<>();

        RestaurantService restaurantService = new RestaurantService(restRepo, itemRepo);
        OrderService orderService = new OrderService(itemRepo, orderRepo, orderItemRepo, restaurantService);

        restaurantService.addRestaurant("A2B", itemDetailsForA2B(), 4);
        restaurantService.addRestaurant("Rasaganga", itemDetailsForRasaganga(), 6);
        restaurantService.addRestaurant("Eat Fit", itemDetailsForEatFit(), 2);

        Order order1 = orderService.placeOrder(Arrays.asList("Idly", "Poori"), "lowest_price_strategy");
        System.out.println(order1);

        Order order2 = orderService.placeOrder(Arrays.asList("Idly", "Vada"), "lowest_price_strategy");
        System.out.println(order2);

        Order order3 = orderService.placeOrder(Arrays.asList("Idly"), "lowest_price_strategy");
        System.out.println(order3);

        orderService.itemPrepared(order1.getId(), order1.getItems().get(0).getId());
        orderService.itemPrepared(order1.getId(), order1.getItems().get(1).getId());

        orderService.itemPrepared(order2.getId(), order2.getItems().get(1).getId());

        Order order4 = orderService.placeOrder(Arrays.asList("Idly"), "lowest_price_strategy");
        System.out.println(order4);
    }

    private static List<ItemDetail> itemDetailsForA2B() {

        ItemDetail item1 = new ItemDetail("Idly", "Idly", 40.0);
        ItemDetail item2 = new ItemDetail("Vada", "Vada", 30.0);
        ItemDetail item3 = new ItemDetail("Paper Plain Dosa", "Paper Plain Dosa", 50);

        return Arrays.asList(item1, item2, item3);
    }

    private static List<ItemDetail> itemDetailsForRasaganga() {

        ItemDetail item1 = new ItemDetail("Idly", "Idly", 45.0);
        ItemDetail item2 = new ItemDetail("Poori", "Poori", 25.0);
        ItemDetail item3 = new ItemDetail("Set Dosa", "Set Dosa", 60.0);

        return Arrays.asList(item1, item2, item3);
    }

    private static List<ItemDetail> itemDetailsForEatFit() {

        ItemDetail item1 = new ItemDetail("Idly", "Idly", 30.0);
        ItemDetail item2 = new ItemDetail("Vada", "Vada", 40.0);

        return Arrays.asList(item1, item2);
    }

}
