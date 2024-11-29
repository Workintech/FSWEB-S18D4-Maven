package com.workintech.s18d1.exceptions;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BurgerException extends RuntimeException {

    private HttpStatus httpStatus;

    public BurgerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}