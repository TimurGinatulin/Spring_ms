package ru.ginatulin.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ginatulin.models.entity.CartEntity;
import ru.ginatulin.models.entity.CartItemEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private List<CartItemDto> items;
    private int totalPrice;

    public CartDto(CartEntity entity) {
        items = new ArrayList<>();
        for (CartItemEntity ent: entity.getItems()){
            items.add(new CartItemDto(ent));
        }
        this.totalPrice = entity.getPrice();
    }
}
