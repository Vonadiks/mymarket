package ru.geekbrains.my.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.my.market.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
//    List<Product> findAllByIdLessThanEqual(Long maxId);
//    List<Product> findAllByIdBetweenAndPriceGreaterThan(Long minId, Long maxId, int minPrice);
//
//    List<Product> findAllByPriceLessThanEqual(int price);
}
