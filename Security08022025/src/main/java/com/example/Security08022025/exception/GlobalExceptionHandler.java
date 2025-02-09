package com.example.Security08022025.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerGlobalException(Exception exception)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY , exception.getMessage());
    }
}
