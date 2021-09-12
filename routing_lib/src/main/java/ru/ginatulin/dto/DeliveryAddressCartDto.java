package ru.ginatulin.dto;

import lombok.Data;

@Data
public class DeliveryAddressCartDto {
    private String address;
    private String phone;

    public DeliveryAddressCartDto(String address) {
        this.address = address;
        this.phone = "empty";
    }
}
