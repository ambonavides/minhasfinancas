package com.reactjs.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactjs.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
