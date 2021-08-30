package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;
import ru.geekbrains.my.market.soap.products.Productsoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

//    public ru.geekbrains.my.market.soap.products.Product findById(Long id) {
//        return productRepository.findById(id);
//    }

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

    public static final Function<Product, Productsoap> functionEntityToSoap = p -> {
        Productsoap productSoap = new Productsoap();
        productSoap.setId(p.getId());
        productSoap.setTitle(p.getTitle());
        productSoap.setPrice(p.getPrice());
        return productSoap;
    };

    public List<Productsoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Productsoap getByIdSoap(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
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
