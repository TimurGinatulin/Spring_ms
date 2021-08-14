package ru.ginatulin.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.users.models.dto.UserDto;
import ru.ginatulin.users.models.entity.RolesEntity;
import ru.ginatulin.users.models.entity.UserEntity;
import ru.ginatulin.users.repository.RolesRepository;
import ru.ginatulin.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserRestService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserEntity> getById(Long id) {
        return Collections.singletonList(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }

    public UserEntity save(UserEntity user) {
        RolesEntity role = rolesRepository.findByTitle("USER")
                .orElseThrow(() -> new NotFoundException("Role not found"));
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserEntity findByLoginAndPassword(String email, String password) {
        UserEntity userEntity = findByEmail(email);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public UserEntity update(UserDto user) {
        UserEntity entity = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getEmail() != null)
            entity.setEmail(user.getEmail());
        if (user.getUserNickname() != null)
            entity.setUserNickname(user.getUserNickname());
        if (user.getFirstName() != null)
            entity.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            entity.setLastName(user.getLastName());
        if (user.getPassword() != null)
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(entity);
    }

    public UserEntity delete(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        entity.setDeleted_at(LocalDateTime.now());
        return userRepository.save(entity);
    }
}
