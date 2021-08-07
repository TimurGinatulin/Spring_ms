package ru.ginatulin.models.entity.entityKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemKey implements Serializable {
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "id_product")
    private Long idProduct;
}
