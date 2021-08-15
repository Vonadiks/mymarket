package ru.geekbrains.my.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.model.OrderItem;
import ru.geekbrains.my.market.model.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
        this.productTitle = product.getTitle();
    }

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.productTitle = orderItem.getProduct().getTitle();
    }

//    private Long productId;
//    private String productTitle;
//    private BigDecimal pricePerProduct;
//    private BigDecimal price;
//    private int quantity;
//
//    public OrderItemDto(Product product) {
//        this.productId = product.getId();
//        this.quantity = 1;
//        this.productTitle = product.getTitle();
//        this.pricePerProduct = product.getPrice();
//        this.price = product.getPrice();
//    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }


}
