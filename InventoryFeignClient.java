package com.hotel.feignclient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.InventoryDTO;

@FeignClient(name = "InventoryMicroservice", url = "http://localhost:8082")
public interface InventoryFeignClient {

    @GetMapping("/inventory/id/{id}")
    InventoryDTO getInventoryItem(@PathVariable int id);
}

