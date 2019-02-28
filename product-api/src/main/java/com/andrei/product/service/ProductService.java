package com.andrei.product.service;

import com.andrei.product.model.Product;
import com.andrei.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getProductByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName").descending());

        return productRepository.findAll(pageable);

    }

    public Optional<Product> getProduct(Long productId) {

        return Optional.ofNullable(productRepository.findById(productId)).orElseThrow(() -> new RuntimeException("Product with id " + productId + " not found"));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
