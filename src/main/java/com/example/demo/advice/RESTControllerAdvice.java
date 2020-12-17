package com.example.demo.advice;

import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RESTControllerAdvice {
    @ExceptionHandler(value = { InvalidEntity.class})
    public ResponseEntity<?> handleCustomException1(Exception ex){
        return ResponseEntity.badRequest().body(new ErrorDto("InvalidEntity",ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        // return new ErrorDetails("LOL", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        //return new ResponseEntity<>(new ErrorDetails("LOL",ex.getMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto  handleCustomException2(InvalidOperationException ex) {
        String msg = ex.getMessage();
        // return ResponseEntity.badRequest().body(new ErrorDetails("LOL",msg,HttpStatus.BAD_REQUEST.value()));
        return new ErrorDto("InvalidOperationException", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        //return new ResponseEntity<>(new ErrorDetails("LOL",ex.getMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto  handleCustomException2(MethodArgumentNotValidException ex) {
        String msg = ex.getMessage();
        // return ResponseEntity.badRequest().body(new ErrorDetails("LOL",msg,HttpStatus.BAD_REQUEST.value()));
        return new ErrorDto("MethodArgumentNotValidException", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        //return new ResponseEntity<>(new ErrorDetails("LOL",ex.getMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
