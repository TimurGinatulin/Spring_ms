package ru.ginatulin.products.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ginatulin.products.models.dto.ProductCartDto;
import ru.ginatulin.products.models.dto.ProductDto;
import ru.ginatulin.products.models.entity.ProductEntity;
import ru.ginatulin.products.service.ProductRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {
    @Autowired
    private ProductRestService productRestService;

    @GetMapping
    @HystrixCommand(fallbackMethod = "exampleMethod2")
    public List<ProductDto> getAllProduct(@RequestParam(required = false) Long id) {
        if (id != null)
            return Stream.of(productRestService.findById(id)).map(ProductDto::new).collect(Collectors.toList());
        return productRestService.findAll();
    }
    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<ProductDto> getListDto(@RequestParam List<Long> ids) {
        return productRestService.findByIds(ids);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ProductEntity addProduct(@RequestBody ProductCartDto productCart) {
        return productRestService.add(productCart);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductEntity deleteOrder(@PathVariable Long id) {
        return productRestService.delete(id);
    }

    public List<ProductDto> exampleMethod(List<Long> ids) {
        return Collections.emptyList();
    }
    public List<ProductDto> exampleMethod2(Long id) {
        return Collections.emptyList();
    }
}
