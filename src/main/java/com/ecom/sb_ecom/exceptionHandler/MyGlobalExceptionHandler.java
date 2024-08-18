package com.ecom.sb_ecom.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String body= e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> myHttpMessageNotReadableException(HttpMessageNotReadableException e){
        String body= e.getMessage();
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e){
        String body= e.getMessage();
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<String> myCategoryAlreadyExistException(CategoryAlreadyExistException e){
        String body= e.getMessage();
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> myConstraintViolationException(ConstraintViolationException e){
        String body= e.getCause().getMessage();
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }
}
