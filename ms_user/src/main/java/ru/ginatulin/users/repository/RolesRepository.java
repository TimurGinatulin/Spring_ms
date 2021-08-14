package ru.ginatulin.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ginatulin.users.models.entity.RolesEntity;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByTitle(String title);
}
