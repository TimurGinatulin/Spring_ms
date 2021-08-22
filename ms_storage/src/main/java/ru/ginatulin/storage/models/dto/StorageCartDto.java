package ru.ginatulin.storage.models.dto;

import lombok.Data;
import ru.ginatulin.storage.models.entity.OccupancyEntity;
import ru.ginatulin.storage.models.entity.StorageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class StorageCartDto {
    private Long id;
    private String title;
    private String address;
    private List<OccupancyCartDto> occupancy;

    public StorageCartDto(StorageEntity entity) {
        this.id = entity.getId();
        this.title= entity.getTitle();
        this.address = entity.getAddress();
        this.occupancy = new ArrayList<>();
        for(OccupancyEntity o: entity.getOccupancy() ) {
            occupancy.add(new OccupancyCartDto(o));
        }
    }
}
