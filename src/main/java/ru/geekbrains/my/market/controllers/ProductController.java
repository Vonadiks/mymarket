package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dto.ProductDto;
import ru.geekbrains.my.market.model.Category;
import ru.geekbrains.my.market.model.Product;
import ru.geekbrains.my.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.my.market.services.CategoryService;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
//        Optional<Product> p = productService.findById(id);
//        if (p.isPresent()) {
//            return new ResponseEntity<>(new ProductDto(p.get()), HttpStatus.OK);
//        }
//        throw new ResourceNotFoundException("Product not found, id: " + id);
        ////return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), "Product not found, id: " + id), HttpStatus.NOT_FOUND);

        //////return new ProductDto(productService.findById(id));
    }

//    @ExceptionHandler
//    public MarketError catchResourceNotFoundException(ResourceNotFoundException e) {
//        return new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
//    }


    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            @RequestParam (name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam (name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam (name = "title", required = false) String title
    ) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return productService.findPage(pageIndex - 1, 5, spec).map(ProductDto::new);
        //return productService.findPage(pageIndex - 1, 5);
    }

//    @PostMapping
//    public ProductDto createNewProduct(@RequestBody ProductDto newProductDTO) {
//        Product product = new Product();
//        product.setTitle(newProductDTO.getTitle());
//        product.setPrice(newProductDTO.getPrice());
//        return new ProductDto(productService.save(product));
//    }
//    @DeleteMapping("/{id}")
//    public Page<ProductDto> deleteById(@PathVariable Long id) {
//        productService.deleteById(id);
//        return findAll(1, spec);
//
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDTO) {
        Product product = new Product();
        product.setTitle(newProductDTO.getTitle());
        product.setPrice(newProductDTO.getPrice());
        String cTitle = newProductDTO.getCategoryTitle();
        Category category = categoryService.findByTitle(cTitle);
        product.setCategory(category);
        return new ProductDto(productService.save(product));
    }


//    @GetMapping("/products}")
//    public Page<Product> findPage(@RequestParam(name = "p") int pageIndex) {
//        return productService.findPage(pageIndex - 1, 5);
//    }

//    @GetMapping("/products/delete/{id}")
//    public List<Product> deleteById(@PathVariable Long id) {
//        productService.deleteById(id);
//        return productService.findAll();
//    }




//    //получение всех товаров [ GET .../app/products ]
//    //http://localhost:8080/mymarket/product/all
//    @GetMapping("/product/all")
//    @ResponseBody
//    public List<Product> findAllProducts() {
//        return productService.findAll();
//    }
//
//    //получение товара по id [ GET .../app/products/{id} ]
//    //http://localhost:8080/mymarket/product/{id}
//
//    @GetMapping("/product/{id}")
//    @ResponseBody
//    public Product findProductById(@PathVariable Long id) {
//        return productService.findById(id);
//    }
//    //удаление товара по id.[ GET .../app/products/delete/{id} ]
//    //http://localhost:8080/mymarket/product/delete/{id}
//
//    @GetMapping("/product/delete/{id}")
//    @ResponseBody
//    public List<Product> deleteProductById(@PathVariable Long id) {
//        productService.deleteById(id);
//        return productService.findAll();
//    }
//
//    //создание нового товара [ POST .../app/products ]
//    //http://localhost:8080/mymarket/product/add?title=prod1&price=10
//
//    @GetMapping("/product/add")
//    @ResponseBody
//    public List<Product> addNewProduct(@RequestParam String title, @RequestParam int price) {
//        saveNewProduct(title, price);
//        return productService.findAll();
//    }
//
//    @PostMapping("/product/add")
//    public String saveNewProduct(@RequestParam String title, @RequestParam int price) {
//        productService.addNewProduct(title, price);
//        return "redirect: /";
//    }
//
//    //товары дороже min цены
//    //http://localhost:8080/mymarket/product/more_than_min_price?price=100
//
//    @GetMapping("/product/more_than_min_price")
//    @ResponseBody
//    public List<Product> findMoreThanMinPrice(@RequestParam  int price) {
//        return productService.findByMinPrice(price);
//    }
//
//    //товары дешевле максимальной цены
//    //http://localhost:8080/mymarket/product/less_than_max_price?price=100
//
//    @GetMapping("/product/less_than_max_price")
//    @ResponseBody
//    public List<Product> findLessThanMaxPrice(@RequestParam  int price) {
//        return productService.findByMaxPrice(price);
//    }
//

//    @GetMapping
//    public String showMainPage(Model model) {
//        model.addAttribute("products", productService.findAll());
//        return "index";
//    }
//
//    @GetMapping("/product/add")
//    public String showAddProductForm() {
//        return "add_product_form";
//    }
//
//    @PostMapping("/product/add")
//    public String addProduct(@RequestParam String title, @RequestParam int price) {
//        productService.addProduct(title, price);
//        return "redirect:/";
//    }
//
//    @GetMapping("/product/{id}")
//    public String showProductInfo(Model model, @PathVariable Long id) {
//        model.addAttribute("product", productService.findById(id));
//       //model.addAttribute("title", productService.findById(id).getTitle());
//        //model.addAttribute("price", productService.findById(id).getPrice());
//        return "product_info_view";
//    }
//
//    @GetMapping("/product/find_by_price")
//    @ResponseBody
//    public List<Product> findByMinPrice(@RequestParam(name = "min") int min) {
//            return productService.findByMinPrice(min);
//    }
//
//    @GetMapping("/product/find_by_id_max")
//    @ResponseBody
//    public List<Product> findByMaxId(@RequestParam(name = "max") Long max) {
//        return productService.findByIdLessMax(max);
//    }


}
