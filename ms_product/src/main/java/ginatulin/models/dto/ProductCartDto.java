package ginatulin.models.dto;

import ginatulin.models.entity.GroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductCartDto {
    private String title;
    private Double price;
    private List<GroupEntity> groups;
}
