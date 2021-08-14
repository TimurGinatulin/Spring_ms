package ru.ginatulin.users.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import ru.ginatulin.users.models.dto.SignUpRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private List<RolesEntity> roles;
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;

    public UserEntity(SignUpRequestDto user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userNickname = user.getUserNickname();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
