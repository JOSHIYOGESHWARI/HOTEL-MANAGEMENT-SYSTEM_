package com.hotel.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Rooms;
import com.hotel.exception.RoomNotFoundException;



@FeignClient(name = "ROOMMICROSERVICE", url = "http://localhost:8098")
public interface RoomFeignClient {

//    @GetMapping("rooms/id/{roomId}")
//    public  Rooms getRoomById(@PathVariable int roomId);
	@GetMapping("rooms/id/{roomId}")
    public ResponseEntity<Rooms> getRoomByRoomId(@PathVariable int roomId) throws RoomNotFoundException;
}
