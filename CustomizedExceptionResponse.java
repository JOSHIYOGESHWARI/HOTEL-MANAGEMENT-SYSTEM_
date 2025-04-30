package com.billing.exception;

import java.time.LocalDate;
import java.util.stream.Collectors;

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

/**
 * Global exception handler for customized response handling.
 * Handles all types of exceptions and validation errors.
 */
@ControllerAdvice
public class CustomizedExceptionResponse extends ResponseEntityExceptionHandler {

    /**
     * Handles all exceptions and returns a custom error response.
     *
     * @param ex       the exception thrown
     * @param request  the web request
     * @return a ResponseEntity containing the error details
     */
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Create a custom error response
        ExceptionResponse response = new ExceptionResponse(
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles runtime exceptions (like resource not found) and returns a custom error response.
     *
     * @param ex       the exception thrown
     * @param request  the web request
     * @return a ResponseEntity containing the error details
     */
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException ex, WebRequest request) {
        // Create a custom error response
        ExceptionResponse response = new ExceptionResponse(
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false),
                "NOT_FOUND"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation errors (MethodArgumentNotValidException) and returns a custom error response.
     *
     * @param ex       the exception thrown
     * @param headers  the HTTP headers
     * @param status   the HTTP status code
     * @param request  the web request
     * @return a ResponseEntity containing the error details
     */


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {	
			    
			    // Fill the code here
    	// Extract field validation errors from the exception
    				BindingResult bindingResult = ex.getBindingResult();
    				String errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage) 																							// field
    						.collect(Collectors.joining(", ")); // Get validation error message for each ,Combine multiple error messages into a single string
    				// Creating a customized error response with validation error messages
    				ExceptionResponse response = new ExceptionResponse(LocalDate.now(), errorMessages, // Use the combined error
    																									// messages here
    						request.getDescription(false), "BAD_REQUEST" // Bad request due to validation errors
    				);
    				// Returning a ResponseEntity with BAD_REQUEST status
    				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
    
}
