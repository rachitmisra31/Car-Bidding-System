package com.carbidding.user_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BIDDING-SERVICE")
public interface BidFeignClient {
    @GetMapping("/bids/cars/{carId}")
    List<BidDto> getBidsByCarId(@PathVariable Long carId);
    @PatchMapping("/bids/assign-winner/{carId}")
    void assigningBid(@PathVariable Long carId);
}
