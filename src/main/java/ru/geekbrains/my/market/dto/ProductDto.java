package ru.geekbrains.my.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String categoryTitle;
    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.categoryTitle = product.getCategory().getTitle();
        this.price = product.getPrice();
    }
}
