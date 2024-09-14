package com.bidding.OrderService.GlobalException;

public class OrderServiceException extends RuntimeException{
    public OrderServiceException(String message)
    {
        super(message);
    }
}
