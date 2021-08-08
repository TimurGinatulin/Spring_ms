package ru.ginatulin.repository;

import ru.ginatulin.models.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
