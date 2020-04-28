package com.reactjs.minhasfinancas.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioDTO {
	
	private String nome;
	private String email;
	private String senha;
}
