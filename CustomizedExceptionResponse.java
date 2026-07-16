package com.hotel.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ControllerAdvice  // Marks this class as a global exception handler for the application
public class CustomizedExceptionResponse extends ResponseEntityExceptionHandler {

    // Handle all other exceptions that don't have a specific handler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    	
        // Create a custom response object with error details
        ExceptionResponse response = new ExceptionResponse(
                LocalDate.now(),  // Current date when the exception occurred
                ex.getMessage(),  // Error message from the exception
                request.getDescription(false),  // Request details, including the exception message
                "INTERNAL_SERVER_ERROR"  // HTTP status description for the error
        );
        // Return the response with an HTTP status code of INTERNAL_SERVER_ERROR
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle "Not Found" exceptions (e.g., resource not found)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException ex, WebRequest request) {
       
        ExceptionResponse response = new ExceptionResponse(
                LocalDate.now(),  
                ex.getMessage(),  
                request.getDescription(false),  // Request details
                "NOT_FOUND"  // HTTP status description for the error
        );
        // Return the response with an HTTP status code of NOT_FOUND
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle validation errors (e.g., method argument validation failures)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // Extract field validation errors from the exception
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessages = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)  // Get validation error message for each field
                .collect(Collectors.joining(", "));  // Combine multiple error messages into a single string

        // Create a customized error response with validation error messages
        ExceptionResponse response = new ExceptionResponse(
                LocalDate.now(),  // The date of the error occurrence
                errorMessages,  // Combined validation error messages
                request.getDescription(false),  // Request details
                "BAD_REQUEST"  // HTTP status description for the error
        );

        // Return a response with HTTP status BAD_REQUEST due to validation errors
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
