package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.model.Category;
import ru.geekbrains.my.market.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;



    public Optional<Category> findById(Long id) {


        return categoryRepository.findById(id);
    }

    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }


}
