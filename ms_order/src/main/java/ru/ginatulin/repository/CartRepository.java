package ru.ginatulin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ginatulin.models.entity.CartEntity;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,String> {
    @Query("select c from Cart c where c.userId = ?1")
    Optional<CartEntity> findByUserId(Long id);
}
