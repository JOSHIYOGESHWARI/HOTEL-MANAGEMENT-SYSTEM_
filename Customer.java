package com.hotel.entity;

import java.time.LocalDate;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
public class Customer {
	
	//Unique id for customer
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	
	//Unique id for customer
	@Column(name="first_name")
	@NotBlank(message="Please Enter First Name")
	private String firstName;
	
	// Last name of the customer
	@Column(name="last_name")
	@NotBlank(message="Please Enter Last Name")
	private String lastName;

	// Email address of the customer
	@Column(name="email")
	@NotBlank(message="Please Enter Valid Email Address")
	@Email(message="Please Enter Valid Email Address")
	private String email;
	
	// Address of the customer
	@Column(name="address")
	@NotBlank(message="Address cant be null")
	@Size(min=5,message="Please Enter Valid Address")
	private String address;
	
	// Date of birth of the customer
	@Column(name="dob")
	@Past(message="Date Should be in past")
	private LocalDate dob;
	
}
