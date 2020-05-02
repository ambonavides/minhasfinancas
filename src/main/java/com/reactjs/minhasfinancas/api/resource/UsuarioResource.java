package com.reactjs.minhasfinancas.api.resource;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactjs.minhasfinancas.api.dto.UsuarioDTO;
import com.reactjs.minhasfinancas.exeption.ErroAutenticacao;
import com.reactjs.minhasfinancas.exeption.RegraNegocioException;
import com.reactjs.minhasfinancas.model.entity.Usuario;
import com.reactjs.minhasfinancas.service.LancamentoService;
import com.reactjs.minhasfinancas.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {
		
		private final UsuarioService service;
		
		private final LancamentoService lancamentoService;
		
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
		
		@GetMapping("{id}/saldo")
		public ResponseEntity obterSaldo(@PathVariable("id") Long id) {
			Optional<Usuario> usuario = service.obterPorId(id);
			
			if(!usuario.isPresent()) {
				 return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
			
			return ResponseEntity.ok(saldo);
		}
	

}
