package com.carbidding.user_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service",url ="localhost:8083")
public interface FeignInterface {
    @GetMapping("/cars/{carId}")
    Car getCarById(@PathVariable Long carId);
    @GetMapping("/cars/users/{userId}")
    List<Car> getCarsByUserId(@PathVariable("userId") Long userId);

    @PutMapping("/cars/{carId}/{userId}/buy")
    void buyCar(@PathVariable("carId") Long carId,@PathVariable Long userId);
    @PutMapping("/cars/{carId}/{userId}/sell")
    void sellCar(@PathVariable Long carId,@PathVariable Long userId);


}
