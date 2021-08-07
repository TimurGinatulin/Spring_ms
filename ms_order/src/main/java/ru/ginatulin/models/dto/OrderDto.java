package ru.ginatulin.models.dto;

import lombok.Data;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;

import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private Long idUser;
    private List<OrderItemEntity> itemList;

    public OrderDto(OrderEntity entity) {
        this.id = entity.getId();
        this.idUser = entity.getIdUser();
        this.itemList = entity.getItemList();
    }
}
