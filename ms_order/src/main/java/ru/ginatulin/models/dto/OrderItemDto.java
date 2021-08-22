package ru.ginatulin.models.dto;

import lombok.Data;
import ru.ginatulin.models.entity.OrderItemEntity;
@Data
public class OrderItemDto {
    private Long idProduct;
    private int quantity;
    private Double price;

    public OrderItemDto(OrderItemEntity entity1) {
        this.idProduct = entity1.getIdProduct();
        this.quantity = entity1.getQuantity();
        this.price = entity1.getPrice();
    }
}
