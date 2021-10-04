package ru.geekbrains.my.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.my.market.model.Category;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.utils.Cart;

import java.math.BigDecimal;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    Cart cart;

    @MockBean
    ProductService productService;

    @Test
    public void saveProductTest() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));
        product.setTitle("Prod100");
        Category category = new Category();
        category.setTitle("Food");
        product.setCategory(category);
        productService.save(product);
        cart.add(product);
        cart.add(product);
        cart.add(product);
        cart.add(product);
        Assertions.assertEquals(4, cart.getItems().size());
    }

}
