package ru.ginatulin.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private Long idUser;
    private List<OrderItemDto> itemList;
}
