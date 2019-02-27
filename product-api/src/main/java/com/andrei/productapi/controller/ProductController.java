package com.andrei.productapi.controller;

import com.andrei.productapi.model.Product;
import com.andrei.productapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class ProductController {
	private final ProductService  productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		Product savedProduct = productService.save(product);
		return ResponseEntity.ok().body("New Product has been saved with ID:" + savedProduct.getId());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		Product product = Optional.ofNullable(productService.getProduct(id)).orElseThrow(() -> new RuntimeException("Product not found!")).get();
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/products")
	public @ResponseBody Page<Product> getProductsByPage(
			@RequestParam(value="pagenumber", defaultValue="0") Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue="20") Integer pageSize) {

		return productService.getProductByPage(pageNumber, pageSize);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		productService.save(product);
		return ResponseEntity.ok().body("Product has been updated successfully.");
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().body("Product has been deleted successfully.");
	}
}
