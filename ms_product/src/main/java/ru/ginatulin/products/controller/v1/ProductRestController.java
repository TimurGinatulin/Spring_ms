package ru.ginatulin.products.controller.v1;

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

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {
    @Autowired
    private ProductRestService productRestService;

    @GetMapping
    public List<ProductDto> getAllOrder(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(productRestService.findById(id)).stream().map(ProductDto::new).collect(Collectors.toList());
        return productRestService.findAll();
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


}
