package com.hotel.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomType {
	
	private int roomTypeId;
	private String roomTypeName;
	private double roomTypePrice;
	private List<Rooms> rooms;
}
