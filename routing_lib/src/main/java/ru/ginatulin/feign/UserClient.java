package ru.ginatulin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ginatulin.dto.UserDto;

import java.util.List;

@FeignClient("user-ms")
public interface UserClient {
    @GetMapping("/api/v1/auth")
    List<UserDto> getAllUsers(@RequestParam(required = false) Long id);
}
