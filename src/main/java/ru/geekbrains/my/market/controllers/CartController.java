package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.my.market.dto.StringResponse;
import ru.geekbrains.my.market.services.CartService;
import ru.geekbrains.my.market.utils.Cart;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), productId);
    }

//    @GetMapping("/{uuid}/remove/{productId}")
//    public void deleteProductFromCart(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
//        Cart cart = cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
//        cart.remove(productId);
//        cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
//    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(principal, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartUuidFromSuffix(principal.getName());
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
//    private final CartService cartService;
//    private final ProductService productService;
//
//    @GetMapping
//    public Cart getCart() {
//        return cartService.getCurrentCart();
//    }
//
//    @GetMapping("/add/{productId}")
//    public void add(@PathVariable Long productId) {
//        Cart cart = cartService.getCurrentCart();
//        if (!cart.add(productId)) {
//            return;
//        }
//        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
//        cart.add(p);
//    }
//
//    @GetMapping("/delete/{productId}")
//    public void deleteProductFromCart(@PathVariable Long productId) {
//        cart.delete(productId);
////        if (cart.delete(productId)) {
////            return;
////        }
////        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
////        cart.delete(p);
//    }
//
//    @GetMapping("/clear")
//    public void clear() {
//        cart.clear();
//    }
//
//    @GetMapping("/decrement/{productId}")
//    public void decrement(@PathVariable Long productId) {
//        cart.changeQuantity(productId, -1);
//    }
//
//
//

}
