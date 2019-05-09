package com.andrei.product.controller;

import com.andrei.contract.product.ProductDTO;
import com.andrei.product.converter.ProductEntityToDTOConverter;
import com.andrei.product.event.ProductEvent;
import com.andrei.product.service.ProductService;
import com.andrei.product.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ProductController extends AbstractController{

	private final ProductService  productService;
	private final ProductEntityToDTOConverter entityToDTOConverter;
	private final ValidationErrorService validationErrorService;

	public ProductController(ProductService productService, ProductEntityToDTOConverter entityToDTOConverter, ValidationErrorService validationErrorService) {
		this.productService = productService;
		this.entityToDTOConverter = entityToDTOConverter;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		ProductDTO productJson = productService.save(productDTO);

		ProductEvent productCreatedEvent = new ProductEvent("One Product created", productJson);
		applicationEventPublisher.publishEvent(productCreatedEvent);

		return ResponseEntity.ok().body("New Product has been saved with ID:" + productJson.getId());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") long id) {

		ProductDTO productJson = productService.getProduct(id);

		ProductEvent productRetrievedEvent = new ProductEvent("Product retrieved", productJson);
		applicationEventPublisher.publishEvent(productRetrievedEvent);

		return ResponseEntity.ok().body(productJson);
	}

	@GetMapping("/products")
	public @ResponseBody
	Page<ProductDTO> getProductsByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return productService.getProductByPage(pageNumber, pageSize);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@Valid @PathVariable("id") long id, @RequestBody ProductDTO productDTO, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		productService.getProduct(id);

		productService.save(productDTO);

		ProductEvent productUpdatedEvent = new ProductEvent("Product updated", productDTO);
		applicationEventPublisher.publishEvent(productUpdatedEvent);

		return ResponseEntity.ok().body("Product has been updated successfully.");
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		productService.getProduct(id);

		productService.deleteProduct(id);
		return ResponseEntity.ok().body("Product has been deleted successfully.");
	}
}
