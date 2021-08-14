package ru.ginatulin.users.repository;

import ru.ginatulin.users.models.entity.OccupancyEntity;
import ru.ginatulin.users.models.entity.entityKeys.OccupancyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupancyRepository extends JpaRepository<OccupancyEntity, OccupancyKey> {
}
