package com.example.restfulwebservice.exception;

import com.example.restfulwebservice.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> allExceptions(Exception exception, WebRequest request) {
        return new ResponseEntity(
                new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity(
                new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }
}
