package ru.ginatulin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.core.models.UserInfo;
import ru.ginatulin.dto.ProductDto;
import ru.ginatulin.dto.UserDto;
import ru.ginatulin.feign.DeliveryClient;
import ru.ginatulin.feign.ProductClient;
import ru.ginatulin.feign.UserClient;
import ru.ginatulin.models.dto.OrderCartDto;
import ru.ginatulin.models.dto.OrderDto;
import ru.ginatulin.models.dto.OrderItemCartDto;
import ru.ginatulin.models.entity.CartEntity;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.models.entity.OrderItemEntity;
import ru.ginatulin.repository.CartRepository;
import ru.ginatulin.repository.OrderItemRepository;
import ru.ginatulin.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRestService {
    @Autowired
    private CartService cartService;
    @Autowired
    private DeliveryClient deliveryClient;
    @Autowired
    private CartRepository cartRepository;
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

    public OrderEntity save(OrderCartDto order, UserClient userClient, ProductClient productClient) {
        List<UserDto> user = userClient.getAllUsers(order.getIdUser());
        if (user.isEmpty())
            throw new NotFoundException("User not found on order: " + order);
        List<OrderItemCartDto> checkListDto = new ArrayList<>();
        for (OrderItemCartDto cart : order.getItemList()) {
            ProductDto product = productClient.getDto(cart.getIdProduct());
            if (product != null) {
                OrderItemCartDto cartDto =
                        new OrderItemCartDto(product.getId(), cart.getQuantity(), product.getPrice());
                checkListDto.add(cartDto);
            }
        }
        order.setItemList(checkListDto);
        OrderEntity orderEntity = new OrderEntity(order);
        OrderEntity saveEntity = orderRepository.save(orderEntity);
        for (OrderItemCartDto itemCartDto : order.getItemList()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity(itemCartDto);
            orderItemEntity.setIdOrder(saveEntity.getId());
            orderItemRepository.save(orderItemEntity);
        }
        return findById(saveEntity.getId());
    }

    public List<OrderDto> findByIds(List<Long> ids) {
        List<OrderDto> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(new OrderDto(findById(id)));
        }
        return list;
    }

    public OrderEntity save(UserInfo userInfo, String cartUuid) {
        CartEntity cartEntity = cartRepository
                .findById(cartUuid).orElseThrow(() -> new NotFoundException("Cart not found"));
        OrderEntity orderEntity = new OrderEntity(cartEntity);
        orderEntity.setIdUser(userInfo.getUserId());
        OrderEntity save = orderRepository.save(orderEntity);
        for (OrderItemEntity itemEntity : save.getItemList()) {
            itemEntity.setIdOrder(save.getId());
            orderItemRepository.save(itemEntity);
        }
        cartService.clearCart(cartUuid);
        return save;
    }

    public List<OrderEntity> findByUserId(Long userId) {
        return orderRepository.findAllByIdUser(userId);
    }
}
