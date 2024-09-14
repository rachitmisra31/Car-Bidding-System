package com.biddingservice.bidding_service.GlobalException;

public class BidServiceException extends RuntimeException{
    public BidServiceException(String message)
    {
        super(message);
    }
}
