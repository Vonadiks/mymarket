package ru.geekbrains.my.market.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.utils.ProductNotFoundException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        for (long i = 1L; i < 6; i++) {
            products.add(new Product(i, "Product" + i, BigDecimal.valueOf(Math.random() * 30).setScale(2, RoundingMode.HALF_UP)));
        }
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id))
                return p;
        }
        throw new ProductNotFoundException();
    }

    public void addProduct(Product product) {
        product.setId(products.stream().mapToLong(Product::getId).max().getAsLong() + 1L);
        products.add(product);
    }
}
