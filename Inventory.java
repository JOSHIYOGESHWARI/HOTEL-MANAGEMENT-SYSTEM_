package com.hotel.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id    // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-generates the primary key 
    @Column(name = "item_id")
    private int itemId;

    @Size(min = 1, message = "Item name is mandatory")   // Ensures the item name is at least 1 character long
    @NotBlank(message = "Item name is mandatory")    // Ensures the item name is not null or empty
    @Column(name = "item_name")
    private String itemName;

    @Min(value = 0, message = "Item quantity is mandatory")     // Ensures the item quantity is a non-negative value
    @Positive(message = "Item quantity is mandatory")          // item quantity should be positive
    @Column(name = "item_quantity")
    private int itemQuantity;

    @Column(name = "timestamp")      // Maps this field to the "timestamp" column in the database
    private LocalDateTime timestamp;

    public Inventory() {
    }
}
