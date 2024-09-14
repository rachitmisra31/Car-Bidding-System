package com.biddingservice.bidding_service.Controller;

import com.biddingservice.bidding_service.Dto.BidDto;
import com.biddingservice.bidding_service.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class bidController {

    @Autowired
    private BidService bidService;
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<BidDto>> getBidsByUserId(@PathVariable Long userId)
    {
        List<BidDto> bids = bidService.getBidsByUserId(userId);
        return ResponseEntity.ok(bids);
    }
    @GetMapping("/cars/{carId}")
    public ResponseEntity<List<BidDto>> getBidsByCarId(@PathVariable Long carId)
    {
        List<BidDto> bids = bidService.getBidsByCarId(carId);
        return ResponseEntity.ok(bids);
    }
    @PostMapping
    public BidDto placeBid(@RequestBody BidDto bidDto)
    {
        return bidService.placeBid(bidDto);

    }
    @PutMapping("/assign-winner/{carId}")
    public Long assignWinningBid(@PathVariable Long carId)
    {
        return bidService.getUserWithHighestBid(carId);

    }
}
