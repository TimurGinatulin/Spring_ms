package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ginatulin.models.entity.DeliveryEntity;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
