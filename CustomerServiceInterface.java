package com.hotel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Rooms;
import com.hotel.entity.Customer;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomNotFoundException;


public interface CustomerServiceInterface {
	
	    Customer addCustomer(Customer customer);
	    Customer updateCustomer(int customerId, Customer customerDetails)throws CustomerNotFoundException;
	    Customer findCustomerById(int customerId) throws CustomerNotFoundException;
	    List<Customer> getAllCustomers();
	    String deleteCustomer(int customerId) throws CustomerNotFoundException;
	    
	    Customer findByEmail(String email);

	    List<Customer> findByFirstName(String firstName);

	    List<Customer> findByAddressContaining(String city);
	
	    //From FeignClient of Room Microservice
	    ResponseEntity<Rooms> getRoomByRoomId(int roomId) throws RoomNotFoundException;
}
