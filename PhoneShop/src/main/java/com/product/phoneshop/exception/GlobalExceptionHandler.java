package com.product.phoneshop.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { HttpClientErrorException.class })

    public ResponseEntity<?> handleHttpClientError(HttpClientErrorException e) {
        ServiceException exception = new ServiceException((HttpStatus) e.getStatusCode(),e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(exception);
    }
}
