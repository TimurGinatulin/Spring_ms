package ru.ginatulin.storage.models.dto;

import lombok.Data;

@Data
public class NewStorageCartDto {
    private Long id;
    private String title;
    private String address;
}
