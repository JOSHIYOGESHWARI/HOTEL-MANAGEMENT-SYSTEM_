package com.billing.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ExceptionResponse class that represents the structure of the error response.
 * This class is used to standardize the format of exception responses.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    /**
     * The timestamp of when the exception occurred.
     */
    private LocalDate timestamp;

    /**
     * The error message associated with the exception.
     */
    private String message;

    /**
     * Additional details regarding the exception.
     */
    private String details;

    /**
     * The HTTP code message associated with the error.
     */
    private String httpCodeMessage;
}
