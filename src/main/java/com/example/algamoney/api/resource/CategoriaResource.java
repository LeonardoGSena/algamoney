package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	private final CategoriaRepository categoriaRepository;

	public CategoriaResource(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void criar(@RequestBody Categoria categoria) {
		categoriaRepository.save(categoria);
	}
}