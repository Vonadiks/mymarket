package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.dto.OrderItemDto;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.my.market.model.Order;
import ru.geekbrains.my.market.model.OrderItem;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.OrderRepository;
import ru.geekbrains.my.market.utils.Cart;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final Cart cart;

    @Transactional
    public void createOrder(String email){
        Order order = new Order();
        order.setEmail(email);
        order.setPrice(cart.getPrice());
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
