package com.example.algamoney.api.domain.service;

import com.example.algamoney.api.domain.exception.BusinessException;
import com.example.algamoney.api.domain.model.Category;
import com.example.algamoney.api.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category addNewCategory(Category category) {
        boolean existsCategories = categoryRepository.findByName(category.getName())
                .stream()
                .anyMatch(existsCategory -> !existsCategory.equals(category));

        if (existsCategories) {
            throw new BusinessException("This category already exists");
        }

        return categoryRepository.save(category);
    }
}
