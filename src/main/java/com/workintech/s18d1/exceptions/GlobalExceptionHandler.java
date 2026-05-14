package com.workintech.s18d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleBurgerException(BurgerException exception) {
        log.error("Burger exception occurred: {}", exception.getMessage());
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleGenericException(Exception exception) {
        log.error("Unexpected exception occurred: {}", exception.getMessage());
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
