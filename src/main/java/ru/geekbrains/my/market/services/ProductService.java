package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findPage(int pageIndex, int pageSize, Specification<Product> spec)
    {

        return productRepository.findAll(spec, (PageRequest.of(pageIndex, pageSize)));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

//    public void addProduct(String title, int price) {
//        Product product = new Product();
//        product.setTitle(title);
//        product.setPrice(price);
//        if (product.getPrice() <= 0){
//            return;
//        }
//        productRepository.save(product);
//    }
//
//    public List<Product> findByMinPrice(int minPrice) {
////        List<Product> all = productRepository.findAll();
////        all.removeIf(p -> p.getPrice() <= minPrice);
////        return all;
//        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
//    }
//
//    public List<Product> findByIdLessMax(Long id) {
////        List<Product> all = productRepository.findAll();
////        all.removeIf(p -> p.getPrice() <= minPrice);
////        return all;
//        return productRepository.findAllByIdLessThanEqual(id);
//    }
//
//    public void deleteById(Long id) {
//        productRepository.deleteById(id);
//    }
//
//
//    public void addNewProduct(String title, int price) {
//        Product product = new Product();
//        product.setTitle(title);
//        product.setPrice(price);
//        if (product.getPrice() <=0) {
//            return;
//        }
//        productRepository.save(product);
//    }
//
//    public List<Product> findByMaxPrice(int price) {
//        return productRepository.findAllByPriceLessThanEqual(price);
//    }
}
