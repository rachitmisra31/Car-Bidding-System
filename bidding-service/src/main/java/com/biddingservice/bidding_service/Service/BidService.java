package com.biddingservice.bidding_service.Service;

import com.biddingservice.bidding_service.Dto.BidDto;

import java.util.List;

public interface BidService {
    List<BidDto> getBidsByUserId(Long userId);
    List<BidDto> getBidsByCarId(Long carId);
    BidDto placeBid(BidDto bidDto);
    Long getUserWithHighestBid(Long carId);
}
