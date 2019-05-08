package com.andrei.category.service;

import com.andrei.category.converter.CategoryDTOToEntityConverter;
import com.andrei.category.converter.CategoryEntityToDTOConverter;
import com.andrei.category.exception.Http404Exception;
import com.andrei.category.model.Category;
import com.andrei.category.repository.CategoryRepository;
import com.andrei.contract.category.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOToEntityConverter dtoToEntityConverter;
    private final CategoryEntityToDTOConverter entityToDTOConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryDTOToEntityConverter dtoToEntityConverter, CategoryEntityToDTOConverter entityToDTOConverter) {
        this.categoryRepository = categoryRepository;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDTOConverter = entityToDTOConverter;
    }

    public Category save(CategoryDTO categoryDTO) {
        Category categoryEntity = dtoToEntityConverter.convert(categoryDTO);
        return categoryRepository.save(categoryEntity);
    }

    public Category update(CategoryDTO categoryDTO, Long categoryId) {
        Category entity = dtoToEntityConverter.convert(categoryDTO);
        entity.setId(categoryId);

        return categoryRepository.save(entity);
    }

    public Page<CategoryDTO> getCategoryByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryName").descending());

        return categoryRepository.findAll(pageable).map(entityToDTOConverter::convert);
    }

    public CategoryDTO getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Http404Exception("Resource not found"));
        return entityToDTOConverter.convert(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
