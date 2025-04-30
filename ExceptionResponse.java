package com.hotel.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ExceptionResponse is a DTO used to standardize the error response format in
 * the application. It contains essential details like the timestamp of the
 * error, the error message, a description of the error, and the HTTP code
 * message.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Getter // Generates getter methods for all fields
@Setter // Generates setter methods for all fields
@AllArgsConstructor // Generates a constructor with all arguments
public class ExceptionResponse {

	/**
	 * The timestamp when the exception occurred.
	 */
	private LocalDate timestamp;

	/**
	 * The error message explaining the exception.
	 */
	private String message;

	/**
	 * Additional details related to the exception, usually including information
	 * about where the exception occurred (e.g., method name or endpoint).
	 */
	private String details;

	/**
	 * A custom HTTP status code message (e.g., "BAD_REQUEST",
	 * "INTERNAL_SERVER_ERROR").
	 */
	private String httpCodeMessage;

}
