package ru.ginatulin.storage.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ru.ginatulin.storage.models.dto.NewStorageCartDto;
import ru.ginatulin.storage.models.dto.StorageCartDto;
import ru.ginatulin.storage.models.entity.StorageEntity;
import ru.ginatulin.storage.service.StorageRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/storages")
public class StorageRestController {
    @Autowired
    private StorageRestService storageRestService;

    @GetMapping
    public List<StorageCartDto> getAllStorages(@RequestParam(required = false) Long id) {
        if (id != null)
            return Stream.of(storageRestService.findById(id)).map(StorageCartDto::new).collect(Collectors.toList());
        return storageRestService.findAll().stream().map(StorageCartDto::new).collect(Collectors.toList());
    }
    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<StorageCartDto> getListDto(@RequestParam List<Long> ids) {
        return storageRestService.findByIds(ids);
    }

    @PostMapping
    public StorageEntity save(@RequestBody NewStorageCartDto cartDto) {
        return storageRestService.save(cartDto);
    }

    @PutMapping
    public StorageEntity update(@RequestBody StorageCartDto storageCartDto) {
        return storageRestService.update(storageCartDto);
    }

    @DeleteMapping("/{id}")
    public StorageEntity delete(@PathVariable Long id) {
        return storageRestService.delete(id);
    }

}
