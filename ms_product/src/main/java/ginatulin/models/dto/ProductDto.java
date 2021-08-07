package ginatulin.models.dto;

import ginatulin.models.entity.ProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private List<GroupEntity> groups;

    public ProductDto(ProductEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.groups = entity.getGroups();
    }
}
