package ru.ginatulin.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ginatulin.core.interfaces.ITokenService;
import ru.ginatulin.core.models.UserInfo;
import ru.ginatulin.dto.CartDto;
import ru.ginatulin.service.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final ITokenService tokenService;

    @PostMapping
    public String createNewCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null) {
            return cartService.getCartForUser(null, null);
        }
        UserInfo userInfo = tokenService.parseToken(token);
        return cartService.getCartForUser(userInfo.getUserId(), null);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable String uuid) {
        return cartService.findById(uuid);
    }

    @PostMapping("/add")
    public void addProductToCart(@RequestParam String uuid, @RequestParam(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam String uuid) {
        cartService.clearCart(uuid);
    }
}