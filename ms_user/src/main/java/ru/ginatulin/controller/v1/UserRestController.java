package ru.ginatulin.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ginatulin.models.entity.UserEntity;
import ru.ginatulin.service.UserRestService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRestService.getAllUsers();
    }
}
