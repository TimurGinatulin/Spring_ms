package ru.ginatulin.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.models.dto.OrderCartDto;
import ru.ginatulin.models.dto.OrderDto;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.models.Response;
import ru.ginatulin.service.OrderRestService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    @Autowired
    private OrderRestService orderRestService;

    @GetMapping
    public List<OrderDto> getAllOrder(@RequestParam(required = false) Long id) {
        if (id != null)
            return Collections.singletonList(orderRestService.findById(id)).stream().map(OrderDto::new).collect(Collectors.toList());
        return orderRestService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public OrderEntity saveOrder(@RequestBody OrderCartDto order) {
        return orderRestService.save(order);
    }

    @DeleteMapping("/{id}")
    public OrderEntity deleteOrder(@PathVariable Long id) {
        return orderRestService.delete(id);
    }


}
