package com.andrei.category.controller;

import com.andrei.category.converter.CategoryEntityToDTOConverter;
import com.andrei.category.event.CategoryEvent;
import com.andrei.category.model.Category;
import com.andrei.category.service.CategoryService;
import com.andrei.category.service.ValidationErrorService;
import com.andrei.contract.category.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
public class CategoryController extends AbstractController{

	private final CategoryService  categoryService;
	private final CategoryEntityToDTOConverter entityToDTOConverter;
	private final ValidationErrorService validationErrorService;

	public CategoryController(CategoryService categoryService, CategoryEntityToDTOConverter entityToDTOConverter, ValidationErrorService validationErrorService) {
		this.categoryService = categoryService;
		this.entityToDTOConverter = entityToDTOConverter;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/categories")
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		Category savedCategory = categoryService.save(categoryDTO);

		CategoryDTO categoryJson = entityToDTOConverter.convert(savedCategory);

		CategoryEvent categoryCreatedEvent = new CategoryEvent("One Category created", categoryJson);
		applicationEventPublisher.publishEvent(categoryCreatedEvent);

		return ResponseEntity.ok().body("New Category has been saved with ID:" + categoryJson.getId());
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") long id) {
		CategoryDTO categoryDTO = categoryService.getCategory(id);

		CategoryEvent categoryRetrievedEvent = new CategoryEvent("Category retrieved", categoryDTO);
		applicationEventPublisher.publishEvent(categoryRetrievedEvent);

		return ResponseEntity.ok().body(categoryDTO);
	}

	@GetMapping("/categories")
	public @ResponseBody Page<CategoryDTO> getCategorysByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return categoryService.getCategoryByPage(pageNumber, pageSize);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory(@NotNull @PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        categoryService.getCategory(id);

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		Category savedCategory = categoryService.update(categoryDTO, id);

		CategoryDTO categoryJson = entityToDTOConverter.convert(savedCategory);

		CategoryEvent categoryUpdatedEvent = new CategoryEvent("Category updated", categoryJson);
		applicationEventPublisher.publishEvent(categoryUpdatedEvent);

		return ResponseEntity.ok().body("Category has been updated successfully.");
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
        categoryService.getCategory(id);

		categoryService.deleteCategory(id);

		return ResponseEntity.ok().body("Category has been deleted successfully.");
	}
}
