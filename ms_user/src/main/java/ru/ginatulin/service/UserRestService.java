package ru.ginatulin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.models.entity.UserEntity;
import ru.ginatulin.repository.UserRepository;

import java.util.List;

@Service
public class UserRestService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
