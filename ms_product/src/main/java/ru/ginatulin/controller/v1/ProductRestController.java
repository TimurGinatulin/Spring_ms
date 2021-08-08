package ru.ginatulin.controller.v1;

import ru.ginatulin.models.dto.ProductCartDto;
import ru.ginatulin.models.dto.ProductDto;
import ru.ginatulin.models.entity.ProductEntity;
import ru.ginatulin.service.ProductRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
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
    public ProductEntity addProduct(@RequestBody ProductCartDto productCart) {
        return productRestService.add(productCart);
    }

    @DeleteMapping("/{id}")
    public ProductEntity deleteOrder(@PathVariable Long id) {
        return productRestService.delete(id);
    }


}
