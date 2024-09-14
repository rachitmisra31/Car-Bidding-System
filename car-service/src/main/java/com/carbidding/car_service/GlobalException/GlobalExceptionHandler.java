package com.carbidding.car_service.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<String> handleGlobalException(GlobalException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex)
    {
        return new ResponseEntity<>("An unexpected error occured: "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
