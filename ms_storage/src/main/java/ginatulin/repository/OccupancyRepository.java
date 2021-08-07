package ginatulin.repository;

import ginatulin.models.entity.OccupancyEntity;
import ginatulin.models.entity.entityKeys.OccupancyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupancyRepository extends JpaRepository<OccupancyEntity, OccupancyKey> {
}
