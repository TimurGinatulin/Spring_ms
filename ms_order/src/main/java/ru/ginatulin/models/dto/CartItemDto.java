package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ginatulin.models.entity.CartItemEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private double pricePerProduct;
    private double price;

    public CartItemDto(CartItemEntity ent) {
        this.productId = ent.getProductId();
        this.productTitle = ent.getTile();
        this.quantity= ent.getQuantity();
        this.pricePerProduct = ent.getPricePerProduct();
        this.price = ent.getPrice();
    }
}
