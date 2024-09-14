package com.bidding.OrderService.Service;

import com.bidding.OrderService.Entity.Order;

import com.bidding.OrderService.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository.findAll().stream().filter(order->order.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
