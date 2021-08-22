package ru.ginatulin.models.dto;

import ru.ginatulin.models.entity.DeliveryEntity;
import lombok.Data;

@Data
public class DeliveryDto {
    private Long id;
    private Long idOrder;
    private DeliveryAddressCartDto address;

    public DeliveryDto(DeliveryEntity entity) {
        this.id = entity.getId();
        this.idOrder = entity.getIdOrder();
        this.address = new DeliveryAddressCartDto(entity.getAddress());
    }
}
