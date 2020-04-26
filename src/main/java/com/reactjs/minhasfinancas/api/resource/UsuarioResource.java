package com.reactjs.minhasfinancas.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactjs.minhasfinancas.api.dto.UsuarioDTO;
import com.reactjs.minhasfinancas.exeption.ErroAutenticacao;
import com.reactjs.minhasfinancas.exeption.RegraNegocioException;
import com.reactjs.minhasfinancas.model.entity.Usuario;
import com.reactjs.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
		
		private UsuarioService service;
		
		public UsuarioResource(UsuarioService service) {
			this.service = service;
		}
		
		@PostMapping("/autenticar")
		public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
			try {
				Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
				return ResponseEntity.ok(usuarioAutenticado);
			}catch(ErroAutenticacao ea) {
				return ResponseEntity.badRequest().body(ea.getMessage());
			}
		}
	
		@PostMapping
		public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
			try {
				Usuario usuario = Usuario.builder()
					.nome(dto.getNome())
					.email(dto.getEmail())
					.senha(dto.getSenha())
					.build();
				Usuario usuarioSalvo = service.salvarUsuario(usuario);
				return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			}catch (RegraNegocioException re) {
				return ResponseEntity.badRequest().body(re.getMessage());
			}
		}
		
		
	

}
