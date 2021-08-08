package ru.ginatulin.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ginatulin.models.dto.UserDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
@Where(clause = "deleted_at is null")
public class UserEntity {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_nickname")
    private String userNickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;

    public UserEntity(UserDto user) {

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userNickname = user.getUserNickname();
        this.email = user.getEmail();
        changePassword(user.getPassword());

    }

    public void changePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        this.password = encoder.encode(password);
    }
}
