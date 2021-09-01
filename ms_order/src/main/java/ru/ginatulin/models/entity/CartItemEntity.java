package ru.ginatulin.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.ginatulin.dto.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private Double pricePerProduct;

    @Column(name = "price")
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CartItemEntity(ProductDto product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = this.pricePerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        price = quantity * pricePerProduct;
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
        price = quantity * pricePerProduct;
    }

    public void decrementQuantity() {
        quantity--;
        price = quantity * pricePerProduct;
    }

}
