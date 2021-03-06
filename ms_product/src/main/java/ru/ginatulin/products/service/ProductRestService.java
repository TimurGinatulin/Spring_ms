package ru.ginatulin.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.products.models.dto.ProductCartDto;
import ru.ginatulin.products.models.dto.ProductDto;
import ru.ginatulin.products.models.entity.ProductEntity;
import ru.ginatulin.products.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<ProductDto> findByIds(List<Long> ids) {
        List<ProductDto> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(new ProductDto(findById(id)));
        }
        return list;
    }

    public Page<ProductDto> findAll(Specification<ProductEntity> spec, Integer page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(this::toDto);
    }

    private ProductDto toDto(ProductEntity product) {
        return new ProductDto(product);
    }
}
