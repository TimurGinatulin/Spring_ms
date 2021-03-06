package ru.ginatulin.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.dto.ProductDto;
import ru.ginatulin.feign.ProductClient;
import ru.ginatulin.models.dto.CartDto;
import ru.ginatulin.models.entity.CartEntity;
import ru.ginatulin.models.entity.CartItemEntity;
import ru.ginatulin.repository.CartRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final ProductClient productClient;
    @Autowired
    private final ModelMapper modelMapper;

    public CartEntity save(CartEntity cart) {
        return cartRepository.save(cart);
    }

    public CartDto findById(String id) {
        return new CartDto(cartRepository.findById(id).orElseThrow(()->new NotFoundException("Cart not found")));
    }

    @Transactional
    public void addToCart(String cartId, Long productId) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(()-> new NotFoundException("User not found"));
        CartItemEntity cartItem = cart.getItemByProductId(productId);
        if (cartItem != null) {
            cartItem.incrementQuantity();
            cart.recalculate();
            return;
        }
        ProductDto p = productClient.getDto(productId);
        cart.add(new CartItemEntity(p));
        cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(String cartId) {
        CartDto cartDto = findById(cartId);
        CartEntity cart = modelMapper.map(cartDto, CartEntity.class);
        cart.setId(cartId);
        cart.clear();
        cartRepository.save(cart);
    }

    public Optional<CartEntity> findByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Transactional
    public String getCartForUser(Long userId, String cartUuid) {
        if (userId != null && cartUuid != null) {
            CartDto cartDto = findById(cartUuid);
            CartEntity cart = modelMapper.map(cartDto, CartEntity.class);
            Optional<CartEntity> oldCart = findByUserId(userId);
            if (oldCart.isPresent()) {
                cart.merge(oldCart.get());
                cartRepository.delete(oldCart.get());
            }
            cart.setUserId(userId);
        }
//        if (userId == null) {
//            CartEntity cart = save(new CartEntity());
//            return cart.getId();
//        }
        Optional<CartEntity> cart = findByUserId(userId);
        if (cart.isPresent()) {
            return cart.get().getId();
        }
        CartEntity newCart = new CartEntity();
        newCart.setUserId(userId);
        save(newCart);
        return newCart.getId();
    }
}