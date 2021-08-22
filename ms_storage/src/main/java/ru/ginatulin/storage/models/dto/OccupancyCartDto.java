package ru.ginatulin.storage.models.dto;

import lombok.Data;
import ru.ginatulin.storage.models.entity.OccupancyEntity;

@Data
public class OccupancyCartDto {
    private Long idProduct;
    private Long quantity;

    public OccupancyCartDto(OccupancyEntity entity) {
        this.idProduct = entity.getIdProduct();
        this.quantity = entity.getQuantity();
    }
}
