package ru.ginatulin.users.repository;

import ru.ginatulin.users.models.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {
    Optional<StorageEntity> findByTitle(String title);

    Optional<StorageEntity> findByAddress(String address);

}
