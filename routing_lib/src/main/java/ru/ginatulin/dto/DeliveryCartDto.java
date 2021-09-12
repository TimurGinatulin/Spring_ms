package ru.ginatulin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCartDto implements Serializable {
    private Long idOrder;
    private DeliveryAddressCartDto address;

    public DeliveryCartDto(Long idOrder, String address) {
        this.idOrder = idOrder;
        this.address = new DeliveryAddressCartDto(address);
    }
}
