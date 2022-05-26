package com.example.algamoney.api.api.controller;

import java.net.URI;
import java.util.List;

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

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;

	@GetMapping
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Category> addNewCategory(@RequestBody Category category, HttpServletResponse response) {
		Category newCategory = categoryService.addNewCategory(category);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(newCategory.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(newCategory);
	}
}