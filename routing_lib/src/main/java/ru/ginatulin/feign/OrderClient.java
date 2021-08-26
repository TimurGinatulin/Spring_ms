package ru.ginatulin.feign;

import ru.ginatulin.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("order-ms")
public interface OrderClient {
    @GetMapping("/api/v1/orders")
     List<OrderDto> getAllOrder(@RequestParam(required = false) Long id) ;
}
