package ru.ginatulin.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.models.FaultedToken;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> template;

    public void saveToken(String token) {
        FaultedToken faultedToken = new FaultedToken(token);
        template.opsForHash().put("TOKEN",faultedToken.getToken(),faultedToken);
    }

    public boolean checkToken(String token) {
       return template.opsForHash().hasKey("TOKEN",token);
    }
}
