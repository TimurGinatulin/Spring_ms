package ru.ginatulin.models.entity;

import ru.ginatulin.models.dto.ProductCartDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "product")
@Where(clause = "deleted_at is null")
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Double price;
    @ManyToMany
    @JoinTable(name = "product_group",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_group", referencedColumnName = "id")}
    )
    private List<GroupEntity> groups;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public ProductEntity(ProductCartDto productCart) {
        this.title = productCart.getTitle();
        this.price = productCart.getPrice();
        this.groups = productCart.getGroups();
    }
}
