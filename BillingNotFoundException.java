package com.billing.exception;

/**
 * Custom exception to be thrown when a billing record is not found.
 * Extends RuntimeException to indicate an unchecked exception.
 */
public class BillingNotFoundException extends RuntimeException {

    /**
     * Constructs a new BillingNotFoundException with the specified detail message.
     *
     * @param message the detail message to be associated with this exception.
     */
    public BillingNotFoundException(String message) {
        super(message);
    }
}
