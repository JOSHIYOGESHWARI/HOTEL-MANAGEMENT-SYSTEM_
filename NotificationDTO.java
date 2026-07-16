package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Data    // Lombok annotation to generate getter, setter, toString, equals, and hashcode methods
@JsonInclude(JsonInclude.Include.NON_NULL)  // Json annotation to exclude null values from the JSON response
public class NotificationDTO {

    private int notificationId;

    private int itemId;
    
    private String type;
  
    private String message;

    private String status;

   
}

