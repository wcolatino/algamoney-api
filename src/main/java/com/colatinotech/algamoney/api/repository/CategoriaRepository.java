package com.colatinotech.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colatinotech.algamoney.api.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
