package ru.ginatulin.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.core.models.UserInfo;
import ru.ginatulin.models.dto.CartDto;
import ru.ginatulin.models.models.Response;
import ru.ginatulin.service.CartService;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public Response createNewCart() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser"))
            return new Response(403,"Not authorize");
        UserInfo userInfo = (UserInfo) principal;
        return new Response(200,cartService.getCartForUser(userInfo.getUserId(), null));
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