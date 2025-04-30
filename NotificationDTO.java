package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDTO {

    private int notificationId;

    private int itemId;
    
    private String type;
  
    private String message;

    private String status;

   
}
