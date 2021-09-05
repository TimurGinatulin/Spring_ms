package ru.ginatulin.controller.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.feign.ProductClient;
import ru.ginatulin.feign.UserClient;
import ru.ginatulin.models.dto.OrderCartDto;
import ru.ginatulin.models.dto.OrderDto;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.service.OrderRestService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    @Autowired
    private OrderRestService orderRestService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ProductClient productClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(defaultFallback = "exampleMethod")
    public List<OrderDto> getAllOrder(@RequestParam(required = false) Long id) {
        if (id != null)
            return Stream.of(orderRestService.findById(id)).map(OrderDto::new).collect(Collectors.toList());
        return orderRestService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "exampleMethod")
    public List<OrderDto> getListDto(@RequestParam List<Long> ids) {
        return orderRestService.findByIds(ids);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public OrderDto saveOrder(@RequestBody OrderCartDto order) {
        OrderEntity save = orderRestService.save(order, userClient, productClient);
        return new OrderDto(save);
    }

    @DeleteMapping("/{id}")
    public OrderDto deleteOrder(@PathVariable Long id) {
        return new OrderDto(orderRestService.delete(id));
    }

    public List<OrderDto> exampleMethod(Long id) {
        return Collections.emptyList();
    }
}
