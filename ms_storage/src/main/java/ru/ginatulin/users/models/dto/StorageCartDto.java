package ru.ginatulin.users.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class StorageCartDto {
    private Long id;
    private String title;
    private String address;
    private List<OccupancyCartDto> occupancy;
}
