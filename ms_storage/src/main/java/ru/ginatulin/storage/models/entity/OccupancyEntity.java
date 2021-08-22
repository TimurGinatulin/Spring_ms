package ru.ginatulin.storage.models.entity;

import ru.ginatulin.storage.models.entity.entityKeys.OccupancyKey;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "occupancy")
@IdClass(OccupancyKey.class)
public class OccupancyEntity {
    @Id
    private Long idStorage;
    @Id
    private Long idProduct;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "id_storage", nullable = false,insertable = false, updatable = false)
    private StorageEntity storage;

    public Long getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(Long idStorage) {
        this.idStorage = idStorage;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
