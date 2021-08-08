package ru.ginatulin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.exceptions.NotFoundException;
import ru.ginatulin.models.dto.UserDto;
import ru.ginatulin.models.entity.UserEntity;
import ru.ginatulin.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserRestService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserEntity> getById(Long id) {
        return Collections.singletonList(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }

    public UserEntity save(UserDto user) {
        return userRepository.save(new UserEntity(user));
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
            entity.changePassword(user.getPassword());
        return userRepository.save(entity);
    }

    public UserEntity delete(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        entity.setDeleted_at(LocalDateTime.now());
        return userRepository.save(entity);
    }
}
