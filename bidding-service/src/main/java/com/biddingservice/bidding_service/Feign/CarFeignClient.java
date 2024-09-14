package com.biddingservice.bidding_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CAR-SERVICE",url ="localhost:8083")
public interface CarFeignClient {
    @GetMapping("/cars/{carId}")
    CarDto getCarsById(@PathVariable Long carId);

   /* @PatchMapping("/cars/{carId}")
    void updateCar(@PathVariable Long carId, @RequestBody CarDto carDto);*/
}
