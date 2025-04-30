package com.hotel.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Jackson annotation to ignore specific properties during serialization

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany; // JPA annotation for defining relationships between entities
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min; // Jakarta validation annotation for numeric constraints
import jakarta.validation.constraints.NotEmpty; // Jakarta validation annotation to ensure the field is not empty
import jakarta.validation.constraints.NotNull; // Jakarta validation annotation to ensure the field is not null
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The RoomType class represents the type of rooms available in the hotel. It
 * includes details such as the room type ID, name, price, and the rooms that
 * belong to this type.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "room_type") 
@JsonIgnoreProperties({ "rooms" }) // Prevents circular references during serialization by ignoring the 'rooms' field
public class RoomType {

	@Id 
	@Column(name = "room_type_id") 
	@NotNull(message = "Room Type Id cannot be null") 
	private int roomTypeId; // Unique identifier for the room type

	@NotEmpty(message = "Room Name cannot be empty") 
	@Column(name = "room_type_name") 
	private String roomTypeName; // The name of the room type (e.g., Single, Double, Suite)

	@Min(value = 500, message = "Room Type price must be greater than 500") 
	@Column(name = "room_type_price") 
	private double roomTypePrice; // The price of the room type

    @JsonIgnore // Preventing circular references from RoomType to Rooms
	@OneToMany(mappedBy = "roomType") // Defines a one-to-many relationship with the Rooms entity
	private List<Rooms> rooms; // A list of rooms that belong to this room type (will be ignored during serialization)
    
 // Custom toString() method to avoid infinite recursion
    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", roomTypePrice=" + roomTypePrice +
                ", roomsCount=" + (rooms != null ? rooms.size() : 0) + // Only print the size of the rooms list
                '}';
    }
 
}
