package ru.ginatulin.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ginatulin.users.models.entity.OrderItemEntity;
import ru.ginatulin.users.models.entity.entityKey.OrderItemKey;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemKey> {

}
