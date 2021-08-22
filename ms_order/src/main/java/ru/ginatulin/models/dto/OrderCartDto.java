package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderCartDto {
    private Long idUser;
    private List<OrderItemCartDto> itemList;
}
