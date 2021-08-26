package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
