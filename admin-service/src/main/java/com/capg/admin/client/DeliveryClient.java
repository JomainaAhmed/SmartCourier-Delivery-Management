package com.capg.admin.client;

import com.capg.admin.dto.AdminDeliveryDto;
import com.capg.admin.dto.DeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.capg.admin.config.FeignConfig;

@FeignClient(name = "DELIVERY-SERVICE", configuration = FeignConfig.class)
public interface DeliveryClient {

    @GetMapping("/deliveries/all")
    List<AdminDeliveryDto> getAllDeliveries();

    @GetMapping("/deliveries/{id}")
    DeliveryDto getDelivery(@PathVariable("id") Long id);

    @PutMapping("/deliveries/{id}/status")
    DeliveryDto updateStatus(@PathVariable("id") Long id,
                             @RequestParam("status") String status);
}