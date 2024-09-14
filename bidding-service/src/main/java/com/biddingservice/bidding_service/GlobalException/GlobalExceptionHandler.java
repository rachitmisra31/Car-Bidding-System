package com.biddingservice.bidding_service.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BidServiceException.class)
    public ResponseEntity<String>handleBiddingServiceException(BidServiceException ex)
    {
        return new ResponseEntity<>("An unexpected error occured: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
