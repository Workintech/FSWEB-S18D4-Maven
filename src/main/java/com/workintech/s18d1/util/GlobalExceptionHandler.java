package com.workintech.s18d1.util;

import com.workintech.s18d1.exceptions.BurgerErrorResponse;
import com.workintech.s18d1.exceptions.BurgerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(BurgerException burgerException){
        BurgerErrorResponse burgerErrorResponse = new BurgerErrorResponse(burgerException.getMessage());
        log.error(burgerException.getMessage());
        return new ResponseEntity<>(burgerErrorResponse,burgerException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception){
        BurgerErrorResponse burgerErrorResponse = new BurgerErrorResponse(exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(burgerErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}