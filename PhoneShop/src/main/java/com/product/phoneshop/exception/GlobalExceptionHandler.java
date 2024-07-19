package com.product.phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)

    public ResponseEntity<?> handleHttpClientError(ServiceException e) {
        //@TODO customize respond dto message
        return ResponseEntity.status(e.getStatus()).body(e);
    }
}