package ru.ginatulin.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private List<GroupDto> groups;
}
