package ru.ginatulin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ginatulin.dto.ProductDto;

import java.util.List;

@FeignClient("product-ms")
public interface ProductClient {
    @GetMapping("/api/v1/products/{id}")
    ProductDto getDto(@PathVariable Long id);

    @GetMapping("/api/v1/products/list")
    List<ProductDto> getListDto(@RequestParam List<Long> ids);
}
