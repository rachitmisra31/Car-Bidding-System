package com.bidding.OrderService.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<String> handleBiddingServiceException(OrderServiceException ex)
    {
        return new ResponseEntity<>("An unexpected error occured: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}