package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long idUser;
    private List<OrderItemDto> itemList;
    private double totalPrice;
    private LocalDateTime timeStamp;

    public OrderDto(OrderEntity entity) {
        this.totalPrice = 0f;
        this.id = entity.getId();
        this.idUser = entity.getIdUser();
        this.itemList = new ArrayList<>();
        this.timeStamp = entity.getTimeStamp();
        if (entity.getItemList() != null) {
            for (OrderItemEntity entity1 : entity.getItemList()) {
                totalPrice += entity1.getPrice();
                itemList.add(new OrderItemDto(entity1));
            }
        }
    }
}
