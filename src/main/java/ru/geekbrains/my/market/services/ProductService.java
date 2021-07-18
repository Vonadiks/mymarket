package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void addProduct(String title, int price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        if (product.getPrice() <= 0){
            return;
        }
        productRepository.save(product);
    }

    public List<Product> findByMinPrice(int minPrice) {
//        List<Product> all = productRepository.findAll();
//        all.removeIf(p -> p.getPrice() <= minPrice);
//        return all;
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findByIdLessMax(Long id) {
//        List<Product> all = productRepository.findAll();
//        all.removeIf(p -> p.getPrice() <= minPrice);
//        return all;
        return productRepository.findAllByIdLessThanEqual(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public void addNewProduct(String title, int price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        if (product.getPrice() <=0) {
            return;
        }
        productRepository.save(product);
    }

    public List<Product> findByMaxPrice(int price) {
        return productRepository.findAllByPriceLessThanEqual(price);
    }
}
