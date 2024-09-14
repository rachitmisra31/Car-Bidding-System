package com.carbidding.user_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE",url = "localhost:8085")
public interface OrderFeignClient {
    @PostMapping("/orders")
    OrderDto createOrder(@RequestBody OrderDto orderDto);
    @GetMapping("/orders/{orderId}")
    OrderDto getOrderById(@PathVariable Long orderId);
    @GetMapping("/orders/user/{userId}")
    List<OrderDto> getOrdersByUserId(@PathVariable Long userId);
}
