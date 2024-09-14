package com.bidding.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE",url = "localhost:8085")
public interface OrderFeignClient {
    @GetMapping("/orders/{orderId}")
    OrderDto getOrderById(@PathVariable("orderId") Long orderId);
}
