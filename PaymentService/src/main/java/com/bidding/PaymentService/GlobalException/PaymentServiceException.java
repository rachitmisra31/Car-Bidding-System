package com.bidding.PaymentService.GlobalException;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String message)
    {
        super(message);
    }
}
