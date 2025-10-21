package com.colatinotech.algamoney.api.repository.lancamento;

import java.util.List;

import com.colatinotech.algamoney.api.model.Lancamento;
import com.colatinotech.algamoney.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter filter);

}
