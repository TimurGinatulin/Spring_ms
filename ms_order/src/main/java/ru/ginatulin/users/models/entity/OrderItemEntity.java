package ru.ginatulin.users.models.entity;

import lombok.NoArgsConstructor;
import ru.ginatulin.users.models.dto.OrderItemCartDto;
import ru.ginatulin.users.models.entity.entityKey.OrderItemKey;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "order_item")
@IdClass(OrderItemKey.class)
public class OrderItemEntity {
    @Id
    private Long idOrder;
    @Id
    private Long idProduct;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false,insertable = false,updatable = false)
    private OrderEntity order;

    public OrderItemEntity(OrderItemCartDto itemCartDto) {
        this.idProduct = itemCartDto.getIdProduct();
        this.quantity = itemCartDto.getQuantity();
        this.price = itemCartDto.getPrice();
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}