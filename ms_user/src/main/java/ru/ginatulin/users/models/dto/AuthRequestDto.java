package ru.ginatulin.users.models.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
