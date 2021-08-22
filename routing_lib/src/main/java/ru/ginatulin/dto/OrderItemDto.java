package ru.ginatulin.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long idProduct;
    private int quantity;
    private Double price;
}
