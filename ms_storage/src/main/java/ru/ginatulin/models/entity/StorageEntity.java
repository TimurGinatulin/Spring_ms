package ru.ginatulin.models.entity;

import ru.ginatulin.models.dto.NewStorageCartDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "storage")
public class StorageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "storage")
    private Set<OccupancyEntity> occupancy;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    public StorageEntity(NewStorageCartDto cartDto) {
        this.title = cartDto.getTitle();
        this.address = cartDto.getAddress();
    }
}
