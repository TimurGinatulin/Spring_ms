package ginatulin.models.dto;

import ginatulin.models.entity.DeliveryAddress;
import ginatulin.models.entity.DeliveryEntity;
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
