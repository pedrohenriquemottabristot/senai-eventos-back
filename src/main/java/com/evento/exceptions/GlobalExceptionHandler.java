package com.evento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<ErrorResponse> handleValidateException
            (BussinesException ex) {
        return new ResponseEntity(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
