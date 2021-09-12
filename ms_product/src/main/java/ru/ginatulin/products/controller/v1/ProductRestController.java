package ru.ginatulin.products.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.products.models.dto.ProductCartDto;
import ru.ginatulin.products.models.dto.ProductDto;
import ru.ginatulin.products.models.entity.ProductEntity;
import ru.ginatulin.products.repository.specification.ProductSpecifications;
import ru.ginatulin.products.service.ProductRestService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {
    @Autowired
    private ProductRestService productRestService;

    @GetMapping
    @HystrixCommand(fallbackMethod = "exampleMethod2")
    public Page<ProductDto> getAllProduct(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productRestService.findAll(ProductSpecifications.build(params), page, 4);
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public ProductDto getDto(@PathVariable Long id) {
        return new ProductDto(productRestService.findById(id));
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
