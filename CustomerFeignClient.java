package com.hotel.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Customer;
import com.hotel.exception.CustomerNotFoundException;

/**
 * Feign client for communicating with the Customer Microservice.
 * 
 * This interface allows the RoomBooking service to interact with the Customer service via RESTful endpoints.
 */
@FeignClient(name = "CUSTOMERMICROSERVICE", url = "http://localhost:8090")
public interface CustomerFeignClient {

    /**
     * Retrieves a customer by their ID from the Customer Microservice.
     * @return a ResponseEntity containing the Customer object if found
     * @throws CustomerNotFoundException if the customer is not found in the system
     */
    @GetMapping("/customers/findcustomerbyid/{customerid}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable int customerid) throws CustomerNotFoundException;
}
