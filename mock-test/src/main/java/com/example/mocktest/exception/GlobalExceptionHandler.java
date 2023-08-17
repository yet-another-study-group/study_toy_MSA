package com.example.mocktest.exception;

import com.example.mocktest.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiResponse<?> handleMockException(TestException ex) {
        return ApiResponse.notContent(ex.getTestRtnConsts(), ex.getMessage());
    }
}
