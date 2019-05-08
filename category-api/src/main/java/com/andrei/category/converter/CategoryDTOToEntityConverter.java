package com.andrei.category.converter;

import com.andrei.category.model.Category;
import com.andrei.contract.category.CategoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOToEntityConverter implements Converter<CategoryDTO, Category> {
    @Override
    public Category convert(CategoryDTO source) {
        return Category.builder()
                .id(source.getId())
                .categoryName(source.getCategoryName())
                .categoryDescription(source.getCategoryDescription())
                .build();
    }
}
