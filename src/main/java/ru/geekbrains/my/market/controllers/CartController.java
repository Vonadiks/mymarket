package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.utils.Cart;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
        cart.add(p);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
        if (cart.delete(productId)) {
            return;
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
        cart.delete(p);
    }

    @GetMapping("/clear")
    public void clear() {
        cart.clear();
    }




}
