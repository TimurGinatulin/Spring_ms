package ru.ginatulin.controller.v1;

import ru.ginatulin.dto.OrderDto;
import ru.ginatulin.feign.OrderClient;
import ru.ginatulin.models.dto.DeliveryCartDto;
import ru.ginatulin.models.dto.DeliveryDto;
import ru.ginatulin.models.entity.DeliveryAddress;
import ru.ginatulin.models.entity.DeliveryEntity;
import ru.ginatulin.service.DeliveryRestService;
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
    @Autowired
    private OrderClient orderClient;

    @GetMapping
    public List<DeliveryDto> getAllDelivery(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(deliveryRestService.findById(id)).stream().map(DeliveryDto::new).collect(Collectors.toList());
        List<OrderDto> allOrder = orderClient.getAllOrder(null);
        for (OrderDto orderDto : allOrder) {
            System.out.println(orderDto.toString());
        }
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
