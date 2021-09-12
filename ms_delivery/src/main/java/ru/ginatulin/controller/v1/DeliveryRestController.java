package ru.ginatulin.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.dto.DeliveryCartDto;
import ru.ginatulin.feign.OrderClient;
import ru.ginatulin.models.dto.DeliveryDto;
import ru.ginatulin.models.entity.DeliveryAddress;
import ru.ginatulin.models.entity.DeliveryEntity;
import ru.ginatulin.service.DeliveryRestService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EnableCircuitBreaker
@RestController
@RequestMapping("/deliveries")
public class DeliveryRestController {
    @Autowired
    private DeliveryRestService deliveryRestService;
    @Autowired
    private OrderClient orderClient;

    @GetMapping
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<DeliveryDto> getAllDelivery(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(deliveryRestService.findById(id)).stream().map(DeliveryDto::new).collect(Collectors.toList());
        return deliveryRestService.findAll();
    }

    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<DeliveryDto> getListDto(@RequestParam List<Long> ids) {
        return deliveryRestService.findByIds(ids);
    }

    @PostMapping
    public void addDelivery(@RequestParam Long orderId, @RequestParam String address) {
        System.out.println("where");
        deliveryRestService.add(new DeliveryCartDto(orderId, address));
    }

    @PutMapping
    public DeliveryEntity update(@RequestBody DeliveryAddress addressCartDto) {
        return deliveryRestService.update(addressCartDto);
    }

    @DeleteMapping("/{id}")
    public DeliveryEntity deleteOrder(@PathVariable Long id) {
        return deliveryRestService.delete(id);
    }

    public List<DeliveryDto> exampleMethod(Long id) {
        System.out.println("Faulted");
        return Collections.emptyList();
    }

}
