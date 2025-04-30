package com.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Rooms class represents the rooms in the hotel. It contains details about
 * the room's status and the type of room it belongs to.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indicates this is a JPA entity that will be mapped to a database table
@Table(name = "rooms") 
public class Rooms {

	@Id // Marks this field as the primary key
	@Column(name = "room_id") 
	@NotNull(message = "Room Id cannot be null") 
	private int roomId; // Unique identifier for each room in the hotel

	@Column(name = "status") 
	@NotNull(message = "Room Status cannot be null") 
	private boolean status; // The current status of the room
	@ManyToOne // Defines a many-to-one relationship between Rooms and RoomType (many rooms can belong to one room type)
	@JoinColumn(name = "room_type_id") // Specifies the foreign key column for the room type
	@JsonIgnore // This prevents serializing the 'roomType' field, preventing circular references during JSON conversion
	private RoomType roomType; 
	
	// Custom toString() method to avoid infinite recursion
    @Override
    public String toString() {
        return "Rooms{" +
                "roomId=" + roomId +
                ", status=" + status +
                ", roomTypeId=" + (roomType != null ? roomType.getRoomTypeId() : null) + // Print only the roomType ID
                '}';
    }
}
