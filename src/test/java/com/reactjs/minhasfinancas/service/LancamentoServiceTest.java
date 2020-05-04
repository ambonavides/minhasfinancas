package com.reactjs.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
public class LancamentoServiceTest {
	

	
}
