package ru.ginatulin.products.models.dto;

import ru.ginatulin.products.models.entity.GroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductCartDto {
    private String title;
    private Double price;
    private List<GroupEntity> groups;
}
