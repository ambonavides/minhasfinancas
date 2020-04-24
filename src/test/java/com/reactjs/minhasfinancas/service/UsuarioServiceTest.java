package com.reactjs.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactjs.minhasfinancas.exeption.ErroAutenticacao;
import com.reactjs.minhasfinancas.exeption.RegraNegocioException;
import com.reactjs.minhasfinancas.model.entity.Usuario;
import com.reactjs.minhasfinancas.model.repository.UsuarioRepository;
import com.reactjs.minhasfinancas.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Before
	public void setup() {
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveSalvarUmUsuario() {
		
	}
	
	@Test
	public void deveAutenticarUmUsuarioComSucesso() {
		String email = "email@email.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		Usuario result = service.autenticar(email, senha);
		
		Assertions.assertThat(result).isNotNull();
	}
	
	@Test(expected = ErroAutenticacao.class)
	public void deveLancarErroQuandoNaoEcontrarErroCadastroComEmailInformado() {
		String email = "email@email.com";
		String senha = "senha";
		
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar(email, senha));
		
		
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Usuario não encontrado para o email informado.");
		
		
	}
	
	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {
		String email = "email@email.com";
		String senha = "senha";
		Usuario usuario = Usuario.builder().email(email).senha(senha).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar(email, "123"));
		
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida.");
	}
	
	
	@Test(expected = Test.None.class)
	public void devevalidarEmail() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		service.validarEmail("email@email.com");
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		service.validarEmail("email@email.com");
	}

}
