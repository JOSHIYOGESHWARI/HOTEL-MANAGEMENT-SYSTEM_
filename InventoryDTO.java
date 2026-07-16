package com.hotel.dto;


import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@Data    // Lombok annotation to generate getter, setter, toString, equals, and hashcode methods
@JsonInclude(JsonInclude.Include.NON_NULL)   // Json annotation to exclude null values from the JSON response
public class InventoryDTO {

    private int itemId;

    private String itemName;

    private int itemQuantity;

    private LocalDateTime timestamp;

 
}
