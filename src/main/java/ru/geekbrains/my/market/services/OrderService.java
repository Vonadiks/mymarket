package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.my.market.dto.OrderDto;
import ru.geekbrains.my.market.dto.OrderItemDto;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.my.market.model.Order;
import ru.geekbrains.my.market.model.OrderItem;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.model.User;
import ru.geekbrains.my.market.repositories.OrderRepository;
import ru.geekbrains.my.market.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Transactional
    public void createOrder(User user, String address, String phone) {
        Cart cart = cartService.getCurrentCart(cartService.getCartUuidFromSuffix(user.getUsername()));
        Order order = new Order();
        order.setPrice(cart.getPrice());
        order.setItems(new ArrayList<>());
        order.setUser(user);
        order.setPhone(phone);
        order.setAddress(address);
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
        cartService.updateCart(cartService.getCartUuidFromSuffix(user.getUsername()), cart);
    }

    @Transactional
    public List<OrderDto> findAllDtosByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }
//    private final OrderRepository orderRepository;
//    private final ProductService productService;
//    private final Cart cart;
//
//    @Transactional
//    public void createOrder(User user, String address, String phone) {
//        Order order = new Order();
//        order.setPrice(cart.getPrice());
//        order.setItems(new ArrayList<>());
//        order.setUser(user);
//        order.setPhone(phone);
//        order.setAddress(address);
//        for (OrderItemDto o : cart.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setQuantity(o.getQuantity());
//            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
//            orderItem.setPricePerProduct(product.getPrice());
//            orderItem.setProduct(product);
//            order.getItems().add(orderItem);
//        }
//        orderRepository.save(order);
//        cart.clear();
//    }
//
//    @Transactional
//    public List<OrderDto> findAllDtosByUsername(String username) {
//        return orderRepository.findAllByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
//    }
//
////    @Transactional
////    public void createOrder(String email){
////        Order order = new Order();
////        order.setEmail(email);
////        order.setPrice(cart.getPrice());
////        order.setItems(new ArrayList<>());
////        for (OrderItemDto o : cart.getItems()) {
////            OrderItem orderItem = new OrderItem();
////            orderItem.setOrder(order);
////            orderItem.setQuantity(o.getQuantity());
////            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
////            orderItem.setPrice(o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
////            orderItem.setPricePerProduct(product.getPrice());
////            orderItem.setProduct(product);
////            order.getItems().add(orderItem);
////        }
////        orderRepository.save(order);
////        cart.clear();
////    }
//
//    public List<Order> findAll(){
//        return orderRepository.findAll();
//    }
}
