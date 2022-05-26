package com.example.algamoney.api.api.controller;

import java.net.URI;
import java.util.List;

import com.example.algamoney.api.api.assembler.CategoryMapper;
import com.example.algamoney.api.api.model.CategoryModel;
import com.example.algamoney.api.api.model.input.CategoryInput;
import com.example.algamoney.api.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.domain.model.Category;
import com.example.algamoney.api.domain.repository.CategoryRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	private CategoryMapper categoryMapper;

	@GetMapping
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
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