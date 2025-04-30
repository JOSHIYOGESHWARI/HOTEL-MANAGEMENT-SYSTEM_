package com.billing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a customer is not found.
 * Extends the Exception class to indicate a checked exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     *
     * @param message the detail message to be associated with this exception.
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
