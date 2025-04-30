package com.hotel.exception;

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
 * Global Exception Handler for the hotel booking service. This class will
 * handle all exceptions and provide customized error responses.
 */
@ControllerAdvice
public class CustomizedExceptionResponse extends ResponseEntityExceptionHandler {

	//Handles all exceptions that are not specifically handled.
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		// Creating a custom error response
		ExceptionResponse response = new ExceptionResponse(LocalDate.now(), ex.getMessage(),
				request.getDescription(false), "INTERNAL_SERVER_ERROR");

		// Returning a ResponseEntity with INTERNAL_SERVER_ERROR status
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handles runtime exceptions like Resource Not Found.
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException ex, WebRequest request) {
		// Creating a custom error response for resource not found
		ExceptionResponse response = new ExceptionResponse(LocalDate.now(), ex.getMessage(),
				request.getDescription(false), "NOT_FOUND");

		// Returning a ResponseEntity with NOT_FOUND status
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// Handles method argument validation failures (e.g. incorrect or missing data in request).
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		// Extract field validation errors from the exception
		BindingResult bindingResult = ex.getBindingResult();
		//Get validation error message for each field
		String errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage) 
				.collect(Collectors.joining(", ")); // Combine multiple error messages into a single string

		// Creating a customized error response with validation error messages
		ExceptionResponse response = new ExceptionResponse(LocalDate.now(), errorMessages, // Use the combined error messages here
				request.getDescription(false), "BAD_REQUEST" // Bad request due to validation errors
		);

		// Returning a ResponseEntity with BAD_REQUEST status
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
