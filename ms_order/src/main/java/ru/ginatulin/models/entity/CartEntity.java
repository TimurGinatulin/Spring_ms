package ru.ginatulin.models.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items;

    @Column(name = "price")
    private int price;
    @Column(name = "user_id")
    private long userId;

    public void add(CartItemEntity cartItem) {
        for (CartItemEntity ci : this.items) {
            if (ci.getProductId() == cartItem.getProductId()) {
                ci.incrementQuantity(cartItem.getQuantity());
                recalculate();
                return;
            }
        }

        this.items.add(cartItem);
        cartItem.setCart(this);
        recalculate();
    }

    public void recalculate() {
        price = 0;
        for (CartItemEntity ci : items) {
            price += ci.getPrice();
        }
    }

    public void clear() {
        for (CartItemEntity ci : items) {
            ci.setCart(null);
        }
        items.clear();
        recalculate();
    }

    public CartItemEntity getItemByProductId(Long productId) {
        for (CartItemEntity ci : items) {
            if (ci.getProductId() == productId) {
                return ci;
            }
        }
        return null;
    }

    public void merge(CartEntity another) {
        for (CartItemEntity ci : another.items) {
            add(ci);
        }
    }

}