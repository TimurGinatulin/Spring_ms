package ru.ginatulin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ginatulin.dto.ProductDto;

import java.util.List;

@FeignClient("product-ms")
public interface ProductClient {
    @GetMapping("/api_v1/products")
    List<ProductDto> getAllOrder(@RequestParam(required = false) Long id);
}
