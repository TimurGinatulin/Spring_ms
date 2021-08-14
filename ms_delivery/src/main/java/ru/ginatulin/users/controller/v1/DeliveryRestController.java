package ru.ginatulin.users.controller.v1;

import ru.ginatulin.users.models.dto.DeliveryCartDto;
import ru.ginatulin.users.models.dto.DeliveryDto;
import ru.ginatulin.users.models.entity.DeliveryAddress;
import ru.ginatulin.users.models.entity.DeliveryEntity;
import ru.ginatulin.users.service.DeliveryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
public class DeliveryRestController {
    @Autowired
    private DeliveryRestService deliveryRestService;

    @GetMapping
    public List<DeliveryDto> getAllOrder(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(deliveryRestService.findById(id)).stream().map(DeliveryDto::new).collect(Collectors.toList());
        return deliveryRestService.findAll();
    }

    @PostMapping
    public DeliveryEntity addProduct(@RequestBody DeliveryCartDto deliveryDto) {
        return deliveryRestService.add(deliveryDto);
    }

    @PutMapping
    public DeliveryEntity update(@RequestBody DeliveryAddress addressCartDto) {
        return deliveryRestService.update(addressCartDto);
    }

    @DeleteMapping("/{id}")
    public DeliveryEntity deleteOrder(@PathVariable Long id) {
        return deliveryRestService.delete(id);
    }


}
