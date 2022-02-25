package com.yoyo.service;

import com.yoyo.dao.Repo;
import com.yoyo.exception.InvalidInputException;
import com.yoyo.model.Item;
import com.yoyo.model.Order;
import com.yoyo.model.OrderItem;
import com.yoyo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {

    private final Repo<Item> itemRepo;
    private final Repo<Order> orderRepo;
    private final Repo<OrderItem> orderItemRepo;
    private final IRestaurantService restaurantService;

    public OrderService(Repo<Item> itemRepo, Repo<Order> orderRepo, Repo<OrderItem> orderItemRepo,
            IRestaurantService restaurantService) {

        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.restaurantService = restaurantService;
    }

    @Override
    public Order placeOrder(List<String> items, String strategy) {

        SelectionStrategy selectionStrategy = StrategyFactory.get(strategy);

        List<OrderItem> orderItems = new ArrayList<>();

        for (String item : items) {
            List<String> restaurantIds = selectionStrategy.getRestaurants(
                    this.itemRepo.getAll().stream().filter(menuItem -> menuItem.getName().equals(item))
                            .collect(Collectors.toList()));

            for (String restaurantId : restaurantIds) {
                if (this.restaurantService.canTakeOrder(restaurantId)) {
                    OrderItem orderItem = new OrderItem(item, restaurantId);
                    orderItems.add(orderItem);
                    this.orderItemRepo.save(orderItem.getId(), orderItem);
                    break;
                }
            }
        }

        if (orderItems.size() > 0) {
            Order order = new Order(orderItems);
            return this.orderRepo.save(order.getId(), order);
        }

        return null;
    }

    @Override
    public void itemPrepared(String orderId, String orderItemId) {

        OrderItem orderItem = this.orderItemRepo.getById(orderItemId);

        if (orderItem == null) {
            throw new InvalidInputException(String.format("order item %s does not exist", orderItemId));
        }

        this.restaurantService.replenish(orderItem.getRestaurantId());
    }
}
