package com.example.algamoney.api.api.controller;

import java.net.URI;
import java.util.List;

import com.example.algamoney.api.api.assembler.CategoryMapper;
import com.example.algamoney.api.api.model.CategoryModel;
import com.example.algamoney.api.api.model.input.CategoryInput;
import com.example.algamoney.api.domain.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.algamoney.api.domain.model.Category;
import com.example.algamoney.api.domain.repository.CategoryRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	private CategoryMapper categoryMapper;
	private CategoryRepository categoryRepository;

	public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryService = categoryService;
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping
	public List<CategoryModel> getCategories() {
		List<Category> categoryList = categoryService.getAllCategories();
		return categoryMapper.toCollectionModel(categoryList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long id) {
		return categoryRepository.findById(id)
				.map(category -> ResponseEntity.ok(categoryMapper.toModel(category)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryModel addNewCategory(@Valid @RequestBody CategoryInput categoryInput, HttpServletResponse response) {
		Category category = categoryMapper.toEntity(categoryInput);
		Category newCategory = categoryService.addNewCategory(category);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(newCategory.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return categoryMapper.toModel(newCategory);
	}
}