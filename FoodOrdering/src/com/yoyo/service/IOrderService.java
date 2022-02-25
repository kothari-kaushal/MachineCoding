package com.yoyo.service;

import com.yoyo.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(List<String> items, String strategy);

    void itemPrepared(String orderId, String orderItemId);

}
