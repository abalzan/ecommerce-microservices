package com.andrei.product.controller;

import com.andrei.product.event.ProductEvent;
import com.andrei.product.exception.ExceptionConstants;
import com.andrei.product.model.Product;
import com.andrei.product.service.ProductService;
import com.andrei.product.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RestController
public class ProductController extends AbstractController{

	private final ProductService  productService;
	private final ValidationErrorService validationErrorService;

	public ProductController(ProductService productService, ValidationErrorService validationErrorService) {
		this.productService = productService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		Product savedProduct = productService.save(product);

		ProductEvent productCreatedEvent = new ProductEvent("One Product created", savedProduct);
		applicationEventPublisher.publishEvent(productCreatedEvent);

		return ResponseEntity.ok().body("New Product has been saved with ID:" + savedProduct.getId());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		Product product = productService.getProduct(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_NOT_FOUND));

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
	public ResponseEntity<?> updateProduct(@Valid @PathVariable("id") long id, @RequestBody Product product, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		productService.getProduct(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));

		productService.save(product);

		ProductEvent productUpdatedEvent = new ProductEvent("Product updated", product);
		applicationEventPublisher.publishEvent(productUpdatedEvent);

		return ResponseEntity.ok().body("Product has been updated successfully.");
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        productService.getProduct(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));

		productService.deleteProduct(id);
		return ResponseEntity.ok().body("Product has been deleted successfully.");
	}
}
