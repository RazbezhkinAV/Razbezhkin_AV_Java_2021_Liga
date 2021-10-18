package com.example.liquibasedemo.exceptionAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity onNotFound(NoSuchElementException cause){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(cause.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgument(IllegalArgumentException cause){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(cause.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity invalidFormat(InvalidFormatException cause){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(cause.getMessage());
    }
}
