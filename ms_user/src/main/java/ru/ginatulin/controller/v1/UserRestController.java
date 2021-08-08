package ru.ginatulin.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.models.dto.UserDto;
import ru.ginatulin.models.entity.UserEntity;
import ru.ginatulin.service.UserRestService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @GetMapping
    public List<UserEntity> getAllUsers(@RequestParam(required = false)Long id) {
        if (id!= null)
            return userRestService.getById(id);
        return userRestService.getAllUsers();
    }
    @PostMapping
    public UserEntity createUser(@RequestBody UserDto user){
        return userRestService.save(user);
    }
    @PutMapping
    public UserEntity updateUser(@RequestBody UserDto user){
        return userRestService.update(user);
    }
    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable Long id){
        return userRestService.delete(id);
    }
}
