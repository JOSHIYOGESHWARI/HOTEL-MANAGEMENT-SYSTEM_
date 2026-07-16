package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.hotel.feignclient")
public class InventoryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMicroserviceApplication.class, args);
	}

}

