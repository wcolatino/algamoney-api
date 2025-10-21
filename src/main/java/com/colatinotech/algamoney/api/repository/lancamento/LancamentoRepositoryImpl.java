package com.colatinotech.algamoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import com.colatinotech.algamoney.api.model.Lancamento;
import com.colatinotech.algamoney.api.repository.filter.LancamentoFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/*
 * Classe responsável por implementar a Query
 * */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lancamento> filtrar(LancamentoFilter filter) {
		
		
		CriteriaBuilder builder = manager.getCriteriaBuilder(); //Responsável por criar as criterias de consulta;
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class); //Responsável por criar as criterios para as queries de consulta;
		
		Root<Lancamento> root = criteria.from(Lancamento.class); //Para criar as restrições
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		
		
		
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		return null;
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!filter.getDescricao().isEmpty()) {
			predicates.add(builder.like(
					builder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%")); // where descricao like '%asdasdsad%' //METAMODEL - regera as classes que forem alteradas;
		}
		
		if (!filter.getDataVencimentoDe().isEmpty()) {
//			predicates.add(null);
		}
		
		if (!filter.getDataVencimentoAte().isEmpty()) {
//			predicates.add(null);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	

}
 