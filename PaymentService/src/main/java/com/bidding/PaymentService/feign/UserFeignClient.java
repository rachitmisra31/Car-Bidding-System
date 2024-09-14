package com.bidding.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE",url ="localhost:8082")
public interface UserFeignClient {
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable("userId")Long userId);
}
