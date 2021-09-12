package ru.ginatulin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ginatulin.dto.DeliveryCartDto;
import ru.ginatulin.dto.DeliveryDto;

import java.util.List;

@FeignClient("delivery-ms")
public interface DeliveryClient {
    @GetMapping("/api/v1/deliveries")
    List<DeliveryDto> getAllDelivery(@RequestParam(required = false) Long id);
    @GetMapping("/api/v1/deliveries/list")
    List<DeliveryDto> getListDto(@RequestParam List<Long> ids);
    @PostMapping("/api/v1/deliveries")
    DeliveryDto addDelivery(DeliveryCartDto deliveryDto);
}

