package ru.ginatulin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ginatulin.models.entity.CartEntity;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,String> {

    Optional<CartEntity> findByUserId(Long id);
}
