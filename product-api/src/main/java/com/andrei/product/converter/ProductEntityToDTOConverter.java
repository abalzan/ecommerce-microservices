package com.andrei.product.converter;

import com.andrei.contract.category.CategoryDTO;
import com.andrei.contract.product.ProductDTO;
import com.andrei.product.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDTOConverter implements Converter<Product, ProductDTO> {
    @Override
    public ProductDTO convert(Product source) {

        CategoryDTO parentCategory = new CategoryDTO(source.getParentCategory().getId(), source.getParentCategory().getCategoryName(), source.getParentCategory().getCategoryDescription());
        CategoryDTO category = new CategoryDTO(source.getCategory().getId(), source.getCategory().getCategoryName(), source.getCategory().getCategoryDescription());

        return ProductDTO.builder()
                .id(source.getId())
                .productCode(source.getProductCode())
                .productName(source.getProductName())
                .shortDescription(source.getShortDescription())
                .longDescription(source.getLongDescription())
                .canDisplay(source.isCanDisplay())
                .isDeleted(source.isDeleted())
                .isAutomotive(source.isAutomotive())
                .isInternational(source.isInternational())
                .parentCategory(parentCategory)
                .category(category)
                .build();
    }
}
