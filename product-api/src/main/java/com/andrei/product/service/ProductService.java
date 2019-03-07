package com.andrei.product.service;

import com.andrei.product.exception.Http400Exception;
import com.andrei.product.exception.Http404Exception;
import com.andrei.product.feign.CategoryFeignClient;
import com.andrei.product.model.Category;
import com.andrei.product.model.Product;
import com.andrei.product.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryFeignClient categoryFeignClient;

    public ProductService(ProductRepository productRepository, CategoryFeignClient categoryFeignClient) {
        this.productRepository = productRepository;
        this.categoryFeignClient = categoryFeignClient;
    }

    @HystrixCommand(fallbackMethod = "saveProductWithouValidation")
    public Product save(Product product) {
        return productRepository.save(validateProductCategories(product));
    }

    public Product saveProductWithouValidation(Product product) {
        log.error("Hystrix circut breaker enabled on saveProductWithouValidation");
        return productRepository.save(product);
    }

    public Page<Product> getProductByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName").descending());

        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProduct(Long productId) {

        return Optional.ofNullable(productRepository.findById(productId)).orElseThrow(() -> new Http404Exception("Resource not found"));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private Product validateProductCategories(Product product) {

        Optional.ofNullable(product.getCategory())
                .map(this::validateCategory).orElseThrow(() -> new Http400Exception("Category does not exists"));

        Optional.ofNullable(product.getParentCategory())
                .map(this::validateCategory)
                .orElseThrow(() -> new Http400Exception("Category does not exists"));

        return product;
    }

    private Category validateCategory(Category category) {
        try {
            return categoryFeignClient.getCategory(category.getId()).getBody();
        } catch (Exception e) {
            log.error("Category {} is invalid", category.getId(), e);
            throw new Http400Exception("Invalid Category");
        }
    }
}
