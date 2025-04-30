package com.hotel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rooms {


	private int roomId;

	private boolean status;

	private RoomType roomType;
	
}
