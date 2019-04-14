package com.andrei.category.service;

import com.andrei.category.exception.Http404Exception;
import com.andrei.category.model.Category;
import com.andrei.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Page<Category> getCategoryByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryName").descending());

        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategory(Long categoryId) {

        return Optional.of(categoryRepository.findById(categoryId)).orElseThrow(() -> new Http404Exception("Resource not found"));
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
