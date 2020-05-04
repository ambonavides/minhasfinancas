package com.reactjs.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.reactjs.minhasfinancas.model.entity.Lancamento;
import com.reactjs.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	public abstract Lancamento salvar(Lancamento lancamento);

	public abstract Lancamento atualizar(Lancamento lancamento);
	
	public abstract void deletar(Lancamento lancamento);
	
	public abstract List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	public abstract void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	public void validar(Lancamento lancametno);
	
	public abstract Optional<Lancamento> obterPorId(Long id);
	
	public abstract BigDecimal obterSaldoPorUsuario(Long idUsuario);
	
	
}
