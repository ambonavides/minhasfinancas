package com.reactjs.minhasfinancas.service;

import java.util.Optional;

import com.reactjs.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

	public abstract Usuario autenticar(String email, String senha);
	
	public abstract Usuario salvarUsuario(Usuario usuario);
	
	public abstract void validarEmail(String email);
	
	public abstract Optional<Usuario> obterPorId(Long id);
	
}
