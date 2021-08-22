package ru.ginatulin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.models.dto.OrderCartDto;
import ru.ginatulin.models.dto.OrderItemCartDto;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;
import ru.ginatulin.repository.OrderItemRepository;
import ru.ginatulin.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderRestService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderEntity delete(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        order.setDeletedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public OrderEntity findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
    }

    public OrderEntity save(OrderCartDto order) {
        OrderEntity orderEntity = new OrderEntity(order);
        OrderEntity saveEntity = orderRepository.save(orderEntity);
        for (OrderItemCartDto itemCartDto : order.getItemList()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity(itemCartDto);
            orderItemEntity.setIdOrder(saveEntity.getId());
            orderItemRepository.save(orderItemEntity);
        }
        return findById(saveEntity.getId());
    }
}
