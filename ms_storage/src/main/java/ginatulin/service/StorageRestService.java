package ginatulin.service;

import ginatulin.exception.ElementExistException;
import ginatulin.exception.NotFoundException;
import ginatulin.models.dto.NewStorageCartDto;
import ginatulin.models.dto.OccupancyCartDto;
import ginatulin.models.dto.StorageCartDto;
import ginatulin.models.entity.OccupancyEntity;
import ginatulin.models.entity.StorageEntity;
import ginatulin.models.entity.entityKeys.OccupancyKey;
import ginatulin.repository.OccupancyRepository;
import ginatulin.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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