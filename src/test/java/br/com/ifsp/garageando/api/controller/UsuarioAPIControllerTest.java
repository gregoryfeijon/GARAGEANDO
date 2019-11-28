package br.com.ifsp.garageando.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.model.UsuarioBuilder;
import br.com.ifsp.garageando.security.enums.Perfil;
import br.com.ifsp.garageando.service.TestHelper;
import br.com.ifsp.garageando.service.UsuarioServiceLogicTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioAPIControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	private TestHelper testHelper;

	private static List<PessoaFisica> pessoas;

	AuthorizationTestHelper<Object> auth = new AuthorizationTestHelper<>();

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		pessoas = testHelper.criarPessoas(false);
	}

	@Test
	public void test01_insereUsuario() {
		UsuarioBuilder usuarioBuilder = new UsuarioBuilder().withLogin(UsuarioServiceLogicTest.USUARIO_LOGIN)
				.withSenha(UsuarioServiceLogicTest.USUARIO_SENHA).withPerfil(Perfil.ROLE_USUARIO)
				.withPessoa(pessoas.get(0));
		Usuario usuario = usuarioBuilder.build();

		HttpEntity<Object> httpEntity = auth.autorizar(usuario, restTemplate);
		ParameterizedTypeReference<Response<Usuario>> tipoRetorno = new ParameterizedTypeReference<Response<Usuario>>() {
		};

		ResponseEntity<Response<Usuario>> resposta = restTemplate.exchange("/api/usuario", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
		Usuario retorno = resposta.getBody().getData();
		assertNotNull(retorno);
	}
}
