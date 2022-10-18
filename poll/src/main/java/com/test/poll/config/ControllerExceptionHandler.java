package com.test.poll.config;

import com.test.poll.exception.ApiError;
import com.test.poll.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ApiError> handleApiException(ApiException e) {

        ApiError apiError = new ApiError(e.getCode(), e.getDescription(), e.getStatusCode());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
}
