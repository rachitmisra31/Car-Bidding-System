package com.bidding.OrderService;

import com.bidding.OrderService.Entity.Order;
import com.bidding.OrderService.Repository.OrderRepository;
import com.bidding.OrderService.Service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Test
    public void testCreateOrder()
    {
        Order order = new Order();
        order.setUserId(1L);
        order.setCarId(2L);
        order.setBidAmount(15000.0);
        doReturn(order).when(orderRepository).save(any(Order.class));
        Order createOrder = orderService.createOrder(order);
        Assertions.assertNotNull(createOrder);
        assertEquals(order.getUserId(),createOrder.getUserId());
        assertEquals(order.getCarId(),createOrder.getCarId());
        assertEquals(order.getBidAmount(),createOrder.getBidAmount());
        verify(orderRepository,times(1)).save(any(Order.class));

    }
    @Test
    public void testGetOrderById()
    {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setCarId(2L);
        order.setBidAmount(15000.0);
        doReturn(Optional.of(order)).when(orderRepository).findById(1L);
        Order foundOrder = orderService.getOrderById(1L);
        Assertions.assertNotNull(foundOrder);
        assertEquals(order.getId(),foundOrder.getId());
        assertEquals(order.getUserId(),foundOrder.getUserId());
        assertEquals(order.getCarId(),foundOrder.getCarId());
        verify(orderRepository,times(1)).findById(1L);

    }
    @Test
    public void testOrderByUserId(){
        Order order1 = new Order();
        order1.setId(1L);
        order1.setUserId(1L);
        order1.setCarId(2L);
        order1.setBidAmount(15000.0);
        Order order2 = new Order();
        order2.setId(2L);
        order2.setUserId(1L);
        order2.setCarId(3L);
        order2.setBidAmount(20000.0);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        doReturn(orders).when(orderRepository).findAll();
        List<Order> userOrders = orderService.getOrderByUserId(1L);
        Assertions.assertNotNull(userOrders);
        assertEquals(2,userOrders.size());
        Assertions.assertTrue(userOrders.stream().allMatch(order->order.getUserId().equals(1L)));
        verify(orderRepository,times(1)).findAll();

    }
}
