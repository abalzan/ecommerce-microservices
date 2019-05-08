package com.andrei.category.converter;

import com.andrei.category.model.Category;
import com.andrei.contract.category.CategoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityToDTOConverter implements Converter<Category, CategoryDTO> {
    @Override
    public CategoryDTO convert(Category source) {
        return CategoryDTO.builder()
                .id(source.getId())
                .categoryName(source.getCategoryName())
                .categoryDescription(source.getCategoryDescription())
                .build();
    }
}
