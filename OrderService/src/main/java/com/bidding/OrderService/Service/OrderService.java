package com.bidding.OrderService.Service;

import com.bidding.OrderService.Entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long orderId);
    List<Order> getOrderByUserId(Long userId);
}
