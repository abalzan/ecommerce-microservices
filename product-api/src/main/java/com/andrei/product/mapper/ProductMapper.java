package com.andrei.product.mapper;

import com.andrei.contract.product.ProductDTO;
import com.andrei.product.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product productDtoToProduct(ProductDTO productDTO);

    ProductDTO productToProductDto(Product product);
}
