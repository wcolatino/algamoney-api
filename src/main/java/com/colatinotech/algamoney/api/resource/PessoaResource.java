package com.colatinotech.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.colatinotech.algamoney.api.event.RecursoCriadoEvent;
import com.colatinotech.algamoney.api.model.Categoria;
import com.colatinotech.algamoney.api.model.Pessoa;
import com.colatinotech.algamoney.api.repository.PessoaRepository;
import com.colatinotech.algamoney.api.service.PessoaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher; //Dispara os eventos na aplicação
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar(){
		List<Pessoa> pessoasEncontradas = repository.findAll();		
		return !pessoasEncontradas.isEmpty() ? ResponseEntity.ok(pessoasEncontradas) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		
		Pessoa pessoaSalva = repository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoa = repository.findById(codigo);
		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Pessoa> deletar(@PathVariable Long codigo){
		Optional<Pessoa> pessoaEncontrada = repository.findById(codigo);
		if (!pessoaEncontrada.isEmpty()) {
			repository.delete(pessoaEncontrada.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar (@Valid @PathVariable Long codigo, @RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = service.atualizar(pessoa, codigo);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) { //Requestbody é obrigatório, se não passar o default é true;
		service.atualizarPropriedadeAtivo(codigo, ativo);		
	}
	
	

}
