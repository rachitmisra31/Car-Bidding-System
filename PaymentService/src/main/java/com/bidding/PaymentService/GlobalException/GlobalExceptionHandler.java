package com.bidding.PaymentService.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(PaymentServiceException.class)
    public ResponseEntity<String> handleBiddingServiceException(PaymentServiceException ex)
    {
        return new ResponseEntity<>("An unexpected error occured: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
