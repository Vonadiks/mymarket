package ru.geekbrains.my.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.my.market.services.ProductService;

import java.math.BigDecimal;

@Controller
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showMainPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/product/add")
    public String showAddProductForm() {
        return "add_product_form";
    }

    @PostMapping("/product/add")
    public String addProduct(@RequestParam String title, @RequestParam BigDecimal price) {
        productService.addProduct(title, price);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String showProductInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
       //model.addAttribute("title", productService.findById(id).getTitle());
        //model.addAttribute("price", productService.findById(id).getPrice());
        return "product_info_view";
    }


}
