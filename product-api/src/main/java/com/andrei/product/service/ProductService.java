package com.andrei.product.service;

import com.andrei.contract.category.CategoryDTO;
import com.andrei.contract.product.ProductDTO;
import com.andrei.product.converter.ProductDTOToEntityConverter;
import com.andrei.product.converter.ProductEntityToDTOConverter;
import com.andrei.product.exception.ExceptionConstants;
import com.andrei.product.feign.CategoryFeignClient;
import com.andrei.product.model.Product;
import com.andrei.product.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryFeignClient categoryFeignClient;
    private final ProductEntityToDTOConverter entityToDTOConverter;
    private final ProductDTOToEntityConverter dtoToEntityConverter;

    public ProductService(ProductRepository productRepository, CategoryFeignClient categoryFeignClient, ProductEntityToDTOConverter entityToDTOConverter, ProductDTOToEntityConverter dtoToEntityConverter) {
        this.productRepository = productRepository;
        this.categoryFeignClient = categoryFeignClient;
        this.entityToDTOConverter = entityToDTOConverter;
        this.dtoToEntityConverter = dtoToEntityConverter;
    }

    public Page<ProductDTO> getProductByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName").descending());

        return productRepository.findAll(pageable).map(entityToDTOConverter::convert);
    }

    public ProductDTO getProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESOURCE_NOT_FOUND));
        return entityToDTOConverter.convert(product);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }


    // fault tolerance
    @HystrixCommand(fallbackMethod = "saveProductWithoutValidation")
    public ProductDTO save(ProductDTO productDTO) {
        validateProductCategories(productDTO);

        final Product savedProduct = productRepository.save(Objects.requireNonNull(dtoToEntityConverter.convert(productDTO)));

        return entityToDTOConverter.convert(savedProduct);
    }

    public ProductDTO saveProductWithoutValidation(ProductDTO productDTO) {
        log.error("Hystrix circuit breaker enabled on saveProductWithoutValidation");
        try {
            Product product = dtoToEntityConverter.convert(productDTO);
            final Product savedProduct = productRepository.save(Objects.requireNonNull(product));

            return entityToDTOConverter.convert(savedProduct);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_REQUEST, e.getCause());
        }
    }

    private ProductDTO validateProductCategories(ProductDTO productDTO) {

        Optional.ofNullable(productDTO.getCategory())
                .map(this::validateCategory).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionConstants.CATEGORY_DOES_NOT_EXISTS));

        Optional.ofNullable(productDTO.getParentCategory())
                .map(this::validateCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionConstants.PARENT_CATEGORY_DOES_NOT_EXISTS));

        return productDTO;
    }

    private CategoryDTO validateCategory(CategoryDTO categoryDTO) {
        try {
            return categoryFeignClient.getCategory(categoryDTO.getId()).getBody();
        } catch (Exception e) {
            log.error("Category {} is invalid", categoryDTO.getId(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_CATEGORY);
        }
    }
}
