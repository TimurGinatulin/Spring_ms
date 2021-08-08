package ru.ginatulin.repository;

import ru.ginatulin.models.entity.OccupancyEntity;
import ru.ginatulin.models.entity.entityKeys.OccupancyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupancyRepository extends JpaRepository<OccupancyEntity, OccupancyKey> {
}
