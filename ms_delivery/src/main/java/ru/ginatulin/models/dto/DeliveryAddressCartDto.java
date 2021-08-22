package ru.ginatulin.models.dto;

import lombok.Data;
import ru.ginatulin.models.entity.DeliveryAddress;

@Data
public class DeliveryAddressCartDto {
    private String address;
    private String phone;

    public DeliveryAddressCartDto(DeliveryAddress address) {
        this.address = address.getAddress();
        this.phone = address.getPhone();
    }
}
