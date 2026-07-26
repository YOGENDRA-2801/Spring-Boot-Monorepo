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

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> localExceptionHandler(NoSuchElementException noSuchElementException) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(noSuchElementException.getMessage()) ;
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> noSuchElementException(@NonNull NoSuchElementException nsee) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) // for user information
                .message(nsee.getMessage())
                .build() ;
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // for actual 404 indication
                .body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFoundException(@NonNull ResourceNotFoundException rnfe) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) // for user information
                .message(rnfe.getMessage())
                .build() ;
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // for actual 404 indication
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generalException(@NonNull Exception e) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // for user information
                .message(e.getMessage())
                .build() ;
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // for actual 404 indication
                .body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validationException(@NonNull MethodArgumentNotValidException manve) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST) // for user information
                .message(manve.getMessage())
                .build() ;
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // for actual 404 indication
                .body(apiError);
    }
}