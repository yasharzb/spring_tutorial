package com.example.test.service;

import com.example.test.model.exception.ExistingStudentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ExistingStudentException.class})
    public void handleUnauthorizedTokenOrUsername() {
        //No scenarios for handling this exception. Only returning http response
    }
}
