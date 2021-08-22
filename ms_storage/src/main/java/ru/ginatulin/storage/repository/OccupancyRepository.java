package ru.ginatulin.storage.repository;

import ru.ginatulin.storage.models.entity.OccupancyEntity;
import ru.ginatulin.storage.models.entity.entityKeys.OccupancyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupancyRepository extends JpaRepository<OccupancyEntity, OccupancyKey> {
}
