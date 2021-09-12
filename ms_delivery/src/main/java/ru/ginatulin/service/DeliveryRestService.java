package ru.ginatulin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.dto.DeliveryCartDto;
import ru.ginatulin.models.dto.DeliveryDto;
import ru.ginatulin.models.entity.DeliveryAddress;
import ru.ginatulin.models.entity.DeliveryEntity;
import ru.ginatulin.repository.AddressRepository;
import ru.ginatulin.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("");
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

    public List<DeliveryDto> findByIds(List<Long> ids) {
        List<DeliveryDto> dtos = new ArrayList<>();
        for (Long id : ids) {
            try {
                dtos.add(deliveryRepository.findById(id).map(DeliveryDto::new)
                        .orElseThrow(() -> new NotFoundException("Delivery with id: " + id + " not found")));
            } catch (NotFoundException e) {
                System.out.println("Delivery with id: " + id + " not found");
            }
        }
        return dtos;
    }
}
