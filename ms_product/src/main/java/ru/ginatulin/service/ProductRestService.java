package ru.ginatulin.service;

import ru.ginatulin.exceptions.NotFoundException;
import ru.ginatulin.models.dto.ProductCartDto;
import ru.ginatulin.models.dto.ProductDto;
import ru.ginatulin.models.entity.ProductEntity;
import ru.ginatulin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRestService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public ProductEntity add(ProductCartDto productCart) {
        return productRepository.save(new ProductEntity(productCart));
    }

    public ProductEntity delete(Long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        entity.setDeletedAt(LocalDateTime.now());
        return productRepository.save(entity);
    }
}
