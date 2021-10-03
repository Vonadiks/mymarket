package ru.geekbrains.my.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BaseSpecification <T> {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String title;
    private Specification<Product> productSpecification;

    public BaseSpecification(BigDecimal minPrice, BigDecimal maxPrice, String title) {
        productSpecification = Specification.where(null);
        if (minPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        if (maxPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        if (title != null) productSpecification = productSpecification.and(ProductSpecifications.titleLike(title));
    }
}
