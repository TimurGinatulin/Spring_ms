package ru.ginatulin.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> template;

    public void saveToken(String token) {
        template.opsForValue().set("TOKEN:" + token, Duration.ofHours(1));
    }

    public boolean checkToken(String token) {
        return template.opsForValue().get("TOKEN:" + token) != null;
    }
}
