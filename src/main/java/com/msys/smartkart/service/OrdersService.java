package com.msys.smartkart.service;

import com.msys.smartkart.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders placeOrder(final Integer userId, final Integer id);

    List<Orders> fetchOrders(final Integer userId);

    void orderCart(final Integer userId);
}
