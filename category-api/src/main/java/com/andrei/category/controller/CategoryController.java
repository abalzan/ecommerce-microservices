package com.andrei.category.controller;

import com.andrei.category.event.CategoryEvent;
import com.andrei.category.exception.Http404Exception;
import com.andrei.category.model.Category;
import com.andrei.category.service.CategoryService;
import com.andrei.category.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class CategoryController extends AbstractController{

	private final CategoryService  categoryService;
	private final ValidationErrorService validationErrorService;

	public CategoryController(CategoryService categoryService, ValidationErrorService validationErrorService) {
		this.categoryService = categoryService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/categories")
	public ResponseEntity<?> createCategory(@Valid @RequestBody Category category, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		Category savedCategory = categoryService.save(category);

		CategoryEvent categoryCreatedEvent = new CategoryEvent("One Category created", savedCategory);
		applicationEventPublisher.publishEvent(categoryCreatedEvent);

		return ResponseEntity.ok().body("New Category has been saved with ID:" + savedCategory.getId());
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") long id) {
		Category category = categoryService.getCategory(id).orElseThrow(() -> new Http404Exception("Category not found!"));

		CategoryEvent categoryRetrievedEvent = new CategoryEvent("Category retrieved", category);
		applicationEventPublisher.publishEvent(categoryRetrievedEvent);

		return ResponseEntity.ok().body(category);
	}

	@GetMapping("/categories")
	public @ResponseBody Page<Category> getCategorysByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return categoryService.getCategoryByPage(pageNumber, pageSize);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory(@Valid @PathVariable("id") long id, @RequestBody Category category, BindingResult result) {
        categoryService.getCategory(id).orElseThrow(() -> new Http404Exception("Category not found!"));

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

        categoryService.save(category);

		CategoryEvent categoryUpdatedEvent = new CategoryEvent("Category updated", category);
		applicationEventPublisher.publishEvent(categoryUpdatedEvent);

		return ResponseEntity.ok().body("Category has been updated successfully.");
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
        categoryService.getCategory(id).orElseThrow(() -> new Http404Exception("Category not found!"));

		categoryService.deleteCategory(id);
		return ResponseEntity.ok().body("Category has been deleted successfully.");
	}
}
