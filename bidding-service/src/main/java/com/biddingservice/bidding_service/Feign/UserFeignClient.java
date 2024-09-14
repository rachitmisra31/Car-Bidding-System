package com.biddingservice.bidding_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url ="localhost:8082")
public interface UserFeignClient {
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable Long userId);
}
