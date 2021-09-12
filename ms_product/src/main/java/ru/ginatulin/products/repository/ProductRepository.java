package ru.ginatulin.products.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.ginatulin.products.models.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

}
