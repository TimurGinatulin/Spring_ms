package ru.ginatulin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ginatulin.dto.StorageCartDto;

import java.util.List;

@FeignClient("storage-ms")
public interface StorageClient {
    @GetMapping("/api_v1/storages")
    List<StorageCartDto> getAllStorages(@RequestParam(required = false) Long id);
}
