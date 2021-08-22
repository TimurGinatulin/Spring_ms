package ru.ginatulin.storage.controller.v1;

import ru.ginatulin.storage.models.dto.NewStorageCartDto;
import ru.ginatulin.storage.models.dto.StorageCartDto;
import ru.ginatulin.storage.models.entity.StorageEntity;
import ru.ginatulin.storage.service.StorageRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storages")
public class StorageRestController {
    @Autowired
    private StorageRestService storageRestService;

    @GetMapping
    public List<StorageCartDto> getAllStorages(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(storageRestService.findById(id))
                    .stream().map(StorageCartDto::new).collect(Collectors.toList());
        return storageRestService.findAll().stream().map(StorageCartDto::new).collect(Collectors.toList());
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
