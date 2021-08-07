package ginatulin.service;

import ginatulin.exception.NotFoundException;
import ginatulin.models.dto.DeliveryAddressCartDto;
import ginatulin.models.dto.DeliveryCartDto;
import ginatulin.models.dto.DeliveryDto;
import ginatulin.models.entity.DeliveryAddress;
import ginatulin.models.entity.DeliveryEntity;
import ginatulin.repository.AddressRepository;
import ginatulin.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DeliveryRestService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private AddressRepository addressRepository;

    public DeliveryEntity findById(Long id) {
        return deliveryRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery not found"));
    }

    public List<DeliveryDto> findAll() {
        return deliveryRepository.findAll().stream().map(DeliveryDto::new).collect(Collectors.toList());
    }

    public DeliveryEntity add(DeliveryCartDto deliveryDto) {
        DeliveryEntity entity = deliveryRepository.save(new DeliveryEntity(deliveryDto));
        addressRepository.save(new DeliveryAddress(deliveryDto.getAddress(), entity.getId()));
        return deliveryRepository.findById(entity.getId()).orElseThrow(() -> new NotFoundException(""));
    }

    public DeliveryEntity delete(Long id) {
        DeliveryEntity entity = deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery not found"));
        entity.setDeletedAt(LocalDateTime.now());
        return deliveryRepository.save(entity);
    }

    public DeliveryEntity update(DeliveryAddress addressCartDto) {
        if (addressCartDto.getAddress() != null) {
            DeliveryEntity delivery = deliveryRepository.findById(addressCartDto.getIdDelivery())
                    .orElseThrow(() -> new NotFoundException("Delivery not found"));
            delivery.getAddress().setAddress(addressCartDto.getAddress());
            return deliveryRepository.save(delivery);
        }
        if (addressCartDto.getPhone() != null) {
            DeliveryEntity delivery = deliveryRepository.findById(addressCartDto.getIdDelivery())
                    .orElseThrow(() -> new NotFoundException("Delivery not found"));
            delivery.getAddress().setPhone(addressCartDto.getAddress());
            return deliveryRepository.save(delivery);
        }
        return null;
    }
}
