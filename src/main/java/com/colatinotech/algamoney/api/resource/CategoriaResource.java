package com.colatinotech.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colatinotech.algamoney.api.model.Categoria;
import com.colatinotech.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> categoriasEncontradas = repository.findAll();		
		return !categoriasEncontradas.isEmpty() ? ResponseEntity.ok(categoriasEncontradas) : ResponseEntity.noContent().build();
	}
	
	
}
