package com.biddingservice.bidding_service.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidDto {
    private Long id;
    private Long userId;
    private Long carId;
    private Double amount;
    private boolean winningBid;
}
