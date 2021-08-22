package ru.ginatulin.products.models.dto;

import ru.ginatulin.products.models.entity.GroupEntity;
import ru.ginatulin.products.models.entity.ProductEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private List<GroupDto> groups;

    public ProductDto(ProductEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.groups = new ArrayList<>();
               for(GroupEntity entity1: entity.getGroups()){
                   groups.add(new GroupDto(entity1));
               }
    }

}
