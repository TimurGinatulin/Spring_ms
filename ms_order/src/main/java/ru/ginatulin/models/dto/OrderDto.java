package ru.ginatulin.models.dto;

import lombok.Data;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private Long idUser;
    private List<OrderItemDto> itemList;

    public OrderDto(OrderEntity entity) {
        this.id = entity.getId();
        this.idUser = entity.getIdUser();
        this.itemList = new ArrayList<>();
        for (OrderItemEntity entity1: entity.getItemList())
        itemList.add(new OrderItemDto(entity1));
    }
}
