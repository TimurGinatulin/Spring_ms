package ru.ginatulin.users.controller.v1;

import ru.ginatulin.users.models.dto.NewStorageCartDto;
import ru.ginatulin.users.models.dto.StorageCartDto;
import ru.ginatulin.users.models.entity.StorageEntity;
import ru.ginatulin.users.service.StorageRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/storages")
public class StorageRestController {
    @Autowired
    private StorageRestService storageRestService;

    @GetMapping
    public List<StorageEntity> getAllStorages(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(storageRestService.findById(id));
        return storageRestService.findAll();
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
