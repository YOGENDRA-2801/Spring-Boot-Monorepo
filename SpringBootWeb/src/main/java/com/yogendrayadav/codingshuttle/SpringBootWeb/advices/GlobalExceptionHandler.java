package com.yogendrayadav.codingshuttle.SpringBootWeb.advices;

import com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException.ResourceNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> noSuchElementException(@NonNull NoSuchElementException nsee) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) // for user information
                .message(nsee.getMessage())
                .build() ;
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(@NonNull ResourceNotFoundException rnfe) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) // for user information
                .message(rnfe.getMessage())
                .build() ;
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> generalException(@NonNull Exception e) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // for user information
                .message(e.getMessage())
                .build() ;
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validationException(@NonNull MethodArgumentNotValidException manve) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST) // for user information
                .message(manve.getMessage())
                .build() ;
        return buildErrorResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse> buildErrorResponseEntity(ApiError apiError) {
        return ResponseEntity
                .status(apiError.getStatus())
                .body(ApiResponse.builder().error(apiError).build()) ;
    }
}