package com.colatinotech.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.colatinotech.algamoney.api.model.Pessoa;
import com.colatinotech.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa atualizar(Pessoa pessoa, Long codigo) {

		Optional<Pessoa> pessoaEncontrada = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(pessoa, pessoaEncontrada.get(), "codigo");
		return repository.save(pessoaEncontrada.get());

	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Optional<Pessoa> pessoaEncontrada = buscarPessoaPeloCodigo(codigo);
		pessoaEncontrada.get().setAtivo(ativo);
		repository.save(pessoaEncontrada.get());

	}

	private Optional<Pessoa> buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoaEncontrada = repository.findById(codigo);

		if (pessoaEncontrada == null) {
			throw new EmptyResultDataAccessException(1); // Esperava 1 elemento, pelo menos e recebeu zero;
		}
		return pessoaEncontrada;
	}

}
