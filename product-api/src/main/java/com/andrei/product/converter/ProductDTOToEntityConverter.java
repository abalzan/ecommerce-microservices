package com.andrei.product.converter;

import com.andrei.contract.product.ProductDTO;
import com.andrei.product.model.Category;
import com.andrei.product.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOToEntityConverter implements Converter<ProductDTO, Product> {

    @Override
    public Product convert(ProductDTO source) {

        Category parentCategory = new Category(source.getParentCategory().getId(), source.getParentCategory().getCategoryName(), source.getParentCategory().getCategoryDescription());
        Category category = new Category(source.getCategory().getId(), source.getCategory().getCategoryName(), source.getCategory().getCategoryDescription());

        return Product.builder()
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