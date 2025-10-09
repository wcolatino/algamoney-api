package com.colatinotech.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colatinotech.algamoney.api.event.RecursoCriadoEvent;
import com.colatinotech.algamoney.api.model.Categoria;
import com.colatinotech.algamoney.api.repository.CategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher; //Dispara os eventos na aplicação
	
	
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
		Categoria categoriaSalva = repository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	
}
