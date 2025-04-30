package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.Rooms;
import com.hotel.entity.Customer;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.service.CustomerServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	// Injecting the CustomerBookingServiceImpl dependency
	private final CustomerServiceImpl customerservice;
	
	public CustomerController(CustomerServiceImpl customerservice) {
		this.customerservice = customerservice;
	}
	
	// Add a new customer
	@PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = customerservice.addCustomer(customer);
     // Returning the newly added customer with HTTP status OK
        return new ResponseEntity<Customer>(newCustomer,HttpStatus.OK);
    }

    // Update an existing customer
    @PutMapping("/update/{customerid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerid, @RequestBody Customer customerDetails) throws CustomerNotFoundException {
        Customer updatedCustomer = customerservice.updateCustomer(customerid, customerDetails);
        if (updatedCustomer != null) {
        	// Returning the updated customer with HTTP status OK
        	return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
        }
     // Returning HTTP status Not Found if the customer does not exist
        return ResponseEntity.notFound().build();
    }

    // Find a customer by ID
    @GetMapping("/findcustomerbyid/{customerid}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable int customerid) throws CustomerNotFoundException {
        Customer customer = customerservice.findCustomerById(customerid);
        if (customer != null) {
        	return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all customers
    @GetMapping("/allcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerservice.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
    }

    // Delete a customer by ID
    @DeleteMapping("/delete/{customerid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerid) throws CustomerNotFoundException {
        String result = customerservice.deleteCustomer(customerid);
        if (result.equals("Customer Deleted")) {
        	// Returning HTTP status Not Found if the customer does not exist
        	return new ResponseEntity<String>("Customer Deleted",HttpStatus.OK);
        }
     // Returning HTTP status Not Found if the customer does not exist
        return ResponseEntity.notFound().build();
    }
    
     // Find a customer by email
    @GetMapping("/findbyemail/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable String email) {
        Customer customer = customerservice.findByEmail(email);
        // Returning the found customer with HTTP status OK
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    
     // Find customers by first name
    @GetMapping("/findbyfname/{firstname}")
    public ResponseEntity<List<Customer>> findByFirstName(@PathVariable String firstname) {
        List<Customer> customers = customerservice.findByFirstName(firstname);
     // Returning the list of customers with the given first name with HTTP status OK
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    
     // Find customers by city
    @GetMapping("/findbycity/{city}")
    public ResponseEntity<List<Customer>> findByAddressContaining(@PathVariable String city) {
        List<Customer> customers = customerservice.findByAddressContaining(city);
     // Returning the list of customers with addresses containing the given city with HTTP status OK
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
 
    // From Feign Client
    // Get room details by room ID using Feign Client
    @GetMapping("/id/{roomId}")
    public ResponseEntity<Rooms> getRoomByRoomId(@PathVariable int roomId) throws RoomNotFoundException{
    	   // Returning the room details with HTTP status OK
        return customerservice.getRoomByRoomId(roomId);
    }

}
