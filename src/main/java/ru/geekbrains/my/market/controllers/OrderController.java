package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dto.OrderDto;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.my.market.model.User;
import ru.geekbrains.my.market.services.OrderService;
import ru.geekbrains.my.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        orderService.createOrder(user, address, phone);
    }

    //    @PostMapping
//    public void createOrder(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).get();
//        System.out.println(user.getEmail());
//        orderService.createOrder(user.getEmail());
//    }
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }
//    @GetMapping
//    public List<OrderDto> loadAllOrders(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).get();
//        return orderService.findAll().stream().filter(u->u.getEmail().equals(user.getEmail())).map(OrderDto::new).collect(Collectors.toList());
//    }

}
