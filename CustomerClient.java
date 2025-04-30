package com.billing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.billing.dto.Customer;

/**
 * Feign client interface for accessing the Customer microservice.
 * This client is used to communicate with the Customer service and fetch customer details.
 */
@FeignClient(name = "CUSTOMERMICROSERVICE", url = "http://localhost:8001")
public interface CustomerClient {

    /**
     * Finds a customer by their ID.
     * This method sends a GET request to the Customer microservice to fetch the customer details 
     * based on the provided customer ID.
     *
     * @param customerId the ID of the customer to find
     * @return the customer details wrapped in a ResponseEntity
     */
    @GetMapping("/customers/findcustomerbyid/{customerId}")
    ResponseEntity<Customer> findCustomerById(@PathVariable int customerId);
}
