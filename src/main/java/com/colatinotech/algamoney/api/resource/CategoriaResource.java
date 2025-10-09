package com.colatinotech.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.colatinotech.algamoney.api.model.Categoria;
import com.colatinotech.algamoney.api.repository.CategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> categoriasEncontradas = repository.findAll();		
		return !categoriasEncontradas.isEmpty() ? ResponseEntity.ok(categoriasEncontradas) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoriaEncontrada = repository.findById(codigo);
		return !categoriaEncontrada.isEmpty() ? ResponseEntity.ok(categoriaEncontrada.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria categoriaNova = repository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaNova.getCodigo()).toUri(); //Pega o código da Uri Atual, adiciona o código e devolve nova URI
//		response.setHeader("Location", uri.toASCIIString());//Devolve o location do Header com a URI salva acima
		return ResponseEntity.created(uri).body(categoriaNova); 
	}
	
	
}
