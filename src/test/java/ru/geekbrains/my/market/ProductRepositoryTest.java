package ru.geekbrains.my.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;
import ru.geekbrains.my.market.utils.BaseSpecification;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void dataBaseTest() {
        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(10, productList.size());
    }

    @Test
    public void filterPriceTest() {
        Specification<Product> baseSpec = new BaseSpecification(new BigDecimal(60), new BigDecimal(90), "Prod_").getProductSpecification();
        List<Product> productList = productRepository.findAll(baseSpec);
        Assertions.assertEquals(4,productList.size());
    }

}
