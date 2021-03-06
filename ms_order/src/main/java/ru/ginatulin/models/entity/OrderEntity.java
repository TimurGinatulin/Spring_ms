package ru.ginatulin.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import ru.ginatulin.models.dto.OrderCartDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@Where(clause = "deleted_at is null")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> itemList;

    public OrderEntity(OrderCartDto dto) {
        this.idUser = dto.getIdUser();
    }
    public OrderEntity(CartEntity cart, Long userId) {
        this.itemList = new ArrayList<>();
        this.idUser = userId;
        for (CartItemEntity ci : cart.getItems()) {
            OrderItemEntity oi = new OrderItemEntity(ci);
            this.itemList.add(oi);
        }
    }

    public OrderEntity(CartEntity cartEntity) {
        this.itemList = new ArrayList<>();
        this.idUser = cartEntity.getUserId();
        for (CartItemEntity item:cartEntity.getItems()){
            itemList.add(new OrderItemEntity(item));
        }
    }
}
