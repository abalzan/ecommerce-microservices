package com.andrei.product.controller;

import com.andrei.product.event.ProductEvent;
import com.andrei.product.exception.Http404Exception;
import com.andrei.product.model.Product;
import com.andrei.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProductController extends AbstractController{
	private final ProductService  productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		Product savedProduct = productService.save(product);

		ProductEvent productCreatedEvent = new ProductEvent("One Product created", savedProduct);
		applicationEventPublisher.publishEvent(productCreatedEvent);

		return ResponseEntity.ok().body("New Product has been saved with ID:" + savedProduct.getId());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		Product product = productService.getProduct(id).orElseThrow(() -> new Http404Exception("Product not found!"));

		ProductEvent productRetrievedEvent = new ProductEvent("Product retrieved", product);
		applicationEventPublisher.publishEvent(productRetrievedEvent);

		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/products")
	public @ResponseBody Page<Product> getProductsByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return productService.getProductByPage(pageNumber, pageSize);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        productService.getProduct(id).orElseThrow(() -> new Http404Exception("Product not found!"));

        productService.save(product);

		ProductEvent productUpdatedEvent = new ProductEvent("Product updated", product);
		applicationEventPublisher.publishEvent(productUpdatedEvent);

		return ResponseEntity.ok().body("Product has been updated successfully.");
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        productService.getProduct(id).orElseThrow(() -> new Http404Exception("Product not found!"));

		productService.deleteProduct(id);
		return ResponseEntity.ok().body("Product has been deleted successfully.");
	}
}
