package ru.ginatulin.models.entity;

import ru.ginatulin.dto.DeliveryCartDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "delivery")
@Where(clause = "deleted_at is null")
public class DeliveryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_order")
    private Long idOrder;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    private DeliveryAddress address;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public DeliveryEntity(DeliveryCartDto deliveryDto) {
        this.idOrder = deliveryDto.getIdOrder();
    }
}
