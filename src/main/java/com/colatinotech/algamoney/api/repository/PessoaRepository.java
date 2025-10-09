package com.colatinotech.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colatinotech.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
