package ru.ginatulin.users.repository;

import ru.ginatulin.users.models.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
