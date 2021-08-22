package ru.ginatulin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ginatulin.models.entity.OrderItemEntity;
import ru.ginatulin.models.entity.entityKey.OrderItemKey;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemKey> {

}
