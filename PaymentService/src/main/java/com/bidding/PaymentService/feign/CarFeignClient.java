package com.bidding.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CAR-SERVICE",url ="localhost:8083")
public interface CarFeignClient {
    @GetMapping("/cars/{carId}")
    CarDto getCarById(@PathVariable("carId") Long carId);
}
