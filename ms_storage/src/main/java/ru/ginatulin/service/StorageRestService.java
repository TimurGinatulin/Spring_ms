package ru.ginatulin.service;

import ru.ginatulin.models.dto.NewStorageCartDto;
import ru.ginatulin.models.dto.OccupancyCartDto;
import ru.ginatulin.models.dto.StorageCartDto;
import ru.ginatulin.models.entity.OccupancyEntity;
import ru.ginatulin.models.entity.StorageEntity;
import ru.ginatulin.models.entity.entityKeys.OccupancyKey;
import ru.ginatulin.repository.OccupancyRepository;
import ru.ginatulin.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.exceptions.ElementExistException;
import ru.ginatulin.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StorageRestService {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private OccupancyRepository occupancyRepository;


    public StorageEntity findById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Storage not found"));
    }

    public List<StorageEntity> findAll() {
        return storageRepository.findAll();
    }

    public StorageEntity save(NewStorageCartDto cartDto) {
        if (storageRepository.findByTitle(cartDto.getTitle()).orElse(null) == null ||
                storageRepository.findByAddress(cartDto.getAddress()).orElse(null) == null) {
            return storageRepository.save(new StorageEntity(cartDto));
        } else
            throw new ElementExistException("Storage already exist");
    }

    public StorageEntity update(StorageCartDto storageCartDto) {
        StorageEntity entity = storageRepository.findById(storageCartDto.getId())
                .orElseThrow(() -> new NotFoundException("Storage not found"));
        if (storageCartDto.getTitle() != null) {
            entity.setTitle(storageCartDto.getTitle());
        }
        if (storageCartDto.getAddress() != null) {
            entity.setAddress(storageCartDto.getAddress());
        }
        if (storageCartDto.getOccupancy() != null) {
            for (OccupancyCartDto cart : storageCartDto.getOccupancy()) {
                OccupancyEntity product = occupancyRepository
                        .findById(new OccupancyKey(storageCartDto.getId(), cart.getIdProduct()))
                        .orElseThrow(() -> new NotFoundException("Product not found at Storage"));
                product.setQuantity(cart.getQuantity());
                occupancyRepository.save(product);
            }
        }
        return storageRepository.save(entity);
    }

    public StorageEntity delete(Long id) {
        StorageEntity entity = storageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Storage not found"));
        entity.setDeletedAt(LocalDateTime.now());
        return storageRepository.save(entity);
    }
}