package com.billing.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Customer DTO (Data Transfer Object) that represents a customer.
 * This class is used to transfer customer data between different layers in the application.
 */
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // Excludes null values in the JSON response
public class Customer {

    // Default constructor for Customer class
    public Customer() {
        // No-arg constructor
    }

    /**
     * The unique identifier for the customer.
     */
    private int customerId;

    /**
     * The first name of the customer.
     */
    private String firstName;

    /**
     * The last name of the customer.
     */
    private String lastName;

    /**
     * The email address of the customer.
     */
    private String email;

    /**
     * The physical address of the customer.
     */
    private String address;

    /**
     * The date of birth of the customer.
     */
    private LocalDate dob;
}
