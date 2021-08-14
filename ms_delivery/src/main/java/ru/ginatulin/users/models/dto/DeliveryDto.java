package ru.ginatulin.users.models.dto;

import ru.ginatulin.users.models.entity.DeliveryAddress;
import ru.ginatulin.users.models.entity.DeliveryEntity;
import lombok.Data;

@Data
public class DeliveryDto {
    private Long id;
    private Long idOrder;
    private DeliveryAddress address;

    public DeliveryDto(DeliveryEntity entity) {
        this.id = entity.getId();
        this.idOrder = entity.getIdOrder();
        this.address = entity.getAddress();
    }
}
