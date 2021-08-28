package ru.ginatulin.users.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.core.interfaces.ITokenService;
import ru.ginatulin.core.models.UserInfo;
import ru.ginatulin.core.services.RedisService;
import ru.ginatulin.users.models.dto.AuthRequestDto;
import ru.ginatulin.users.models.dto.AuthResponseDto;
import ru.ginatulin.users.models.dto.SignUpRequestDto;
import ru.ginatulin.users.models.dto.UserDto;
import ru.ginatulin.users.models.entity.UserEntity;
import ru.ginatulin.users.service.UserRestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class UserRestController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRestService userRestService;

    @Autowired
    private ITokenService iTokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        UserEntity user = new UserEntity(signUpRequest);
        userRestService.save(user);
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestHeader("Authorization") String header) {
        redisService.saveToken(header.replace("Bearer ", ""));
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        UserEntity user = userRestService.findByLoginAndPassword(request.getEmail(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getTitle()));
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .role(roles)
                .build();
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<UserDto> getAllUsers(@RequestParam(required = false) Long id) {
        if (id != null)
            return userRestService.getById(id).stream().map(UserDto::new).collect(Collectors.toList());
        return userRestService.getAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "exampleMethod2")
    public List<UserDto> getListDto(@RequestParam List<Long> ids) {
        return userRestService.findByIds(ids);
    }

    @PutMapping
    public UserEntity updateUser(@RequestBody UserDto user) {
        return userRestService.update(user);
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable Long id) {
        return userRestService.delete(id);
    }

    public List<UserDto> exampleMethod(Long id) {
        return Collections.emptyList();
    }
    public List<UserDto> exampleMethod2(List<Long> ids) {
        return Collections.emptyList();
    }
}
