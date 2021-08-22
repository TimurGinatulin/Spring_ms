package ru.ginatulin.users.models.dto;

import lombok.Data;
import ru.ginatulin.users.models.entity.UserEntity;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userNickname;
    private String email;
    private String password;

    public UserDto(UserEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.userNickname = entity.getUserNickname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
