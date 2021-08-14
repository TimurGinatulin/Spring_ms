package ru.ginatulin.users.models.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String userNickname;
    private String email;
    private String password;
}
