package ru.ginatulin.models.dto;

import lombok.Data;

@Data
public class DeliveryCartDto {
    private Long idOrder;
    private DeliveryAddressCartDto address;
}
