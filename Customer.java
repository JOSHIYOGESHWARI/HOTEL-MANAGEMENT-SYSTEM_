package com.hotel.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Customer class is a Data Transfer Object (DTO) that represents a customer
 * entity in the system. This object is used for transferring customer data
 * between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ensures that null values are not included in the JSON response
public class Customer {

	private int customerId; // Unique identifier for the customer

	private String firstName; // First name of the customer

	private String lastName; // Last name of the customer

	private String email; // Email address of the customer

	private String address; // Address of the customer

	private LocalDate dob; // Date of birth of the customer
}
