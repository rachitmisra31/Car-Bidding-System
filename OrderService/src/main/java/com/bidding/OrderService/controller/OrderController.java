package com.bidding.OrderService.controller;

import com.bidding.OrderService.Entity.Order;
import com.bidding.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
    @GetMapping("/user/{userId}")
    public List<Order> getOrderByUserId(@PathVariable Long userId){
        return orderService.getOrderByUserId(userId);
    }
    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
}
