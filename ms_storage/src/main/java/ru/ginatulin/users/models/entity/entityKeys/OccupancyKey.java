package ru.ginatulin.users.models.entity.entityKeys;

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
public class OccupancyKey implements Serializable {
    @Column(name = "id_storage")
    private Long idStorage;
    @Column(name = "id_product")
    private Long idProduct;
}
