package com.billing.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;

/**
 * Entity class representing a Billing record.
 * This class maps to the "billing" table in the database.
 * It contains various fields related to billing information, such as amount, 
 * method of payment, and customer details.
 */
@Entity
@Table(name = "billing")
@Data
public class Billing {

    /**
     * The unique ID of the billing record. This is the primary key in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billingId;

    /**
     * The total amount of the bill. This includes the room price and any applicable charges like GST.
     */
    private double amount;

    /**
     * The status of the billing. Can be "Pending", "Paid", etc.
     */
    private String status;

    /**
     * The method of payment for the billing (e.g., "Cash", "Credit Card").
     * This field cannot be blank or null.
     */
    @NotBlank(message = "Please provide method of payment")
    @NotNull(message = "Please enter any method of payment")
    private String method;

    /**
     * The date and time when the billing record was created.
     */
    private LocalDateTime billingDate;

    /**
     * The booking ID related to this billing record.
     * The ID must be a positive number.
     */
    @Positive(message = "Enter a valid booking ID")
    private int bookingId;

    /**
     * The customer ID related to this billing record.
     * The ID must be a positive number.
     */
    @Positive(message = "Enter a valid customer ID")
    private int customerId;

    /**
     * The name of the customer associated with this billing record.
     */
    private String customerName;

    /**
     * The price of the room associated with this billing record.
     */
    private double roomPrice;

    /**
     * Default constructor for the Billing entity.
     */
    public Billing() {
        super();
    }
}
