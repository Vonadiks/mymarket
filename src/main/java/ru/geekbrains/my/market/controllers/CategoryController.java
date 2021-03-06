package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.my.market.dto.CategoryDto;
import ru.geekbrains.my.market.model.Category;
import ru.geekbrains.my.market.services.CategoryService;
import ru.geekbrains.my.market.exceptions.ResourceNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        Category c = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new CategoryDto(c);
    }

}
