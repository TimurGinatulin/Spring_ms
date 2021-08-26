package ru.ginatulin.feign;

import ru.ginatulin.dto.DeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("delivery-ms")
public interface DeliveryClient {
    @GetMapping("/api/v1/deliveries")
    List<DeliveryDto> getAllDelivery(@RequestParam(required = false) Long id);
}

