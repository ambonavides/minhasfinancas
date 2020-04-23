package com.reactjs.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactjs.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public abstract boolean existsByEmail(String nome);
	
	
}
