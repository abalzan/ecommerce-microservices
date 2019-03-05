package com.andrei.category.controller;

import com.andrei.category.event.CategoryEvent;
import com.andrei.category.exception.Http404Exception;
import com.andrei.category.model.Category;
import com.andrei.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CategoryController extends AbstractController{
	private final CategoryService  categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/categories")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
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
	public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        categoryService.getCategory(id).orElseThrow(() -> new Http404Exception("Category not found!"));

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
