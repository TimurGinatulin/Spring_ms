package ru.ginatulin.products.models.dto;

import lombok.Data;
import ru.ginatulin.products.models.entity.GroupEntity;

@Data
public class GroupDto {
    private Long id;
    private String title;

    public GroupDto(GroupEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
