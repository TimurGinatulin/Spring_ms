package ru.ginatulin.users.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemCartDto {
    private Long idProduct;
    private int quantity;
    private Double price;
}
