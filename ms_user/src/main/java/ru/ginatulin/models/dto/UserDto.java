package ru.ginatulin.models.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userNickname;
    private String email;
    private String password;
}
