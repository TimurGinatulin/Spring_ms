package ru.ginatulin.dto;

import lombok.Data;

@Data
public class DeliveryDto {
    private Long id;
    private Long idOrder;
    private DeliveryAddressCartDto address;

}
