package br.com.ifsp.garageando.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.model.UsuarioBuilder;
import br.com.ifsp.garageando.model.UsuarioDTOBuilder;
import br.com.ifsp.garageando.security.enums.Perfil;
import br.com.ifsp.garageando.service.TestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioAPIControllerTest {

	private static final String USUARIO_DELETAR_LOGIN = "BADASOMMELIER";
	private static final String USUARIO_DELETAR_SENHA = "latao123";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TestHelper testHelper;

	private static List<PessoaFisica> pessoas;

	AuthorizationTestHelper<Object> auth = new AuthorizationTestHelper<>();

	private Usuario criaUsuario(String login, String senha, Perfil perfil, PessoaFisica pessoa) {
		UsuarioBuilder usuarioBuilder = new UsuarioBuilder().withLogin(login).withSenha(senha).withPerfil(perfil)
				.withPessoa(pessoa);
		return usuarioBuilder.build();
	}

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		pessoas = testHelper.criarPessoas(false);
	}

	@Test
	public void test01_insereUsuario() {
		Usuario usuario = criaUsuario(TestHelper.USUARIO_LOGIN,
				TestHelper.USUARIO_SENHA, Perfil.ROLE_USUARIO, pessoas.get(0));

		HttpEntity<Object> httpEntity = auth.autorizar(usuario, restTemplate);
		ParameterizedTypeReference<Response<Usuario>> tipoRetorno = new ParameterizedTypeReference<Response<Usuario>>() {
		};

		ResponseEntity<Response<Usuario>> resposta = restTemplate.exchange("/api/usuario", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

		Usuario retorno = resposta.getBody().getData();
		assertNotNull(retorno);
	}

	@Test
	public void test02_tentaInserirUsuarioSemPessoa() {
		UsuarioBuilder usuarioBuilder = new UsuarioBuilder()
				.withLogin(TestHelper.USUARIO_LOGIN + Math.random())
				.withSenha(TestHelper.USUARIO_SENHA + Math.random()).withPerfil(Perfil.ROLE_USUARIO);
		Usuario usuario = usuarioBuilder.build();

		HttpEntity<Object> httpEntity = auth.autorizar(usuario, restTemplate);
		ParameterizedTypeReference<Response<Usuario>> tipoRetorno = new ParameterizedTypeReference<Response<Usuario>>() {
		};

		ResponseEntity<Response<Usuario>> resposta = restTemplate.exchange("/api/usuario", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().getErrors()
				.contains("Erro! Deve ser feito um cadastro de pessoa física junto com o de usuário!"));
	}

	@Test
	public void test03_findUsuarioById() {
		UsuarioDTOBuilder usuarioDTOBuilder = new UsuarioDTOBuilder().withId(1L);
		UsuarioDTO usuarioDTO = usuarioDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(usuarioDTO, restTemplate);
		ParameterizedTypeReference<Response<UsuarioDTO>> tipoRetorno = new ParameterizedTypeReference<Response<UsuarioDTO>>() {
		};

		ResponseEntity<Response<UsuarioDTO>> resposta = restTemplate.exchange("/api/usuario/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Usuario usuario = resposta.getBody().getData().getUsuario();
		assertNotNull(usuario);
		assertEquals(usuario.getId(), Long.valueOf(1L));
	}

	@Test
	public void test04_findUsuarioByEmail() {
		UsuarioDTOBuilder usuarioDTOBuilder = new UsuarioDTOBuilder().withEmail(pessoas.get(0).getEmail());
		UsuarioDTO usuarioDTO = usuarioDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(usuarioDTO, restTemplate);
		ParameterizedTypeReference<Response<UsuarioDTO>> tipoRetorno = new ParameterizedTypeReference<Response<UsuarioDTO>>() {
		};

		ResponseEntity<Response<UsuarioDTO>> resposta = restTemplate.exchange("/api/usuario/email", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Usuario usuario = resposta.getBody().getData().getUsuario();
		assertNotNull(usuario);
		assertEquals(usuario.getPessoa().getEmail(), pessoas.get(0).getEmail());
	}

	@Test
	public void test05_findUsuarioByLogin() {
		UsuarioDTOBuilder usuarioDTOBuilder = new UsuarioDTOBuilder().withLogin(TestHelper.USUARIO_LOGIN);
		UsuarioDTO usuarioDTO = usuarioDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(usuarioDTO, restTemplate);
		ParameterizedTypeReference<Response<UsuarioDTO>> tipoRetorno = new ParameterizedTypeReference<Response<UsuarioDTO>>() {
		};

		ResponseEntity<Response<UsuarioDTO>> resposta = restTemplate.exchange("/api/usuario/login", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Usuario usuario = resposta.getBody().getData().getUsuario();
		assertNotNull(usuario);
		assertEquals(usuario.getLogin(), TestHelper.USUARIO_LOGIN);
	}

	@Test
	public void test06_listAllUsuarios() {
		HttpEntity<String> httpEntity = auth.autorizar(restTemplate);
		ParameterizedTypeReference<List<Usuario>> tipoRetorno = new ParameterizedTypeReference<List<Usuario>>() {
		};
		ResponseEntity<List<Usuario>> response = restTemplate.exchange("/api/usuario", HttpMethod.GET, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertTrue(response.getBody().size() != 0);
		List<Usuario> usuarios = response.getBody();
		Usuario usuario = usuarios.get(0);
		assertNotNull(usuario);
	}

	@Test
	public void test07_AlteraUsuarioEPessoa() {
		UsuarioDTOBuilder usuarioDTOBuilder = new UsuarioDTOBuilder().withId(2L);
		UsuarioDTO usuarioDTO = usuarioDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(usuarioDTO, restTemplate);
		ParameterizedTypeReference<Response<UsuarioDTO>> tipoRetorno = new ParameterizedTypeReference<Response<UsuarioDTO>>() {
		};

		ResponseEntity<Response<UsuarioDTO>> resposta = restTemplate.exchange("/api/usuario/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertNotNull(resposta.getBody().getData().getUsuario());
		Usuario usuario = resposta.getBody().getData().getUsuario();
		String loginNovo = TestHelper.USUARIO_LOGIN + Math.random();
		usuario.setLogin(loginNovo);
		usuario.getPessoa().setEmail("mudou.mudou@gmail.com");

		HttpEntity<Object> httpEntityEdit = auth.autorizar(usuario, restTemplate);
		ParameterizedTypeReference<Response<Usuario>> tipoRetornoEdit = new ParameterizedTypeReference<Response<Usuario>>() {
		};

		ResponseEntity<Response<Usuario>> respostaEdit = restTemplate.exchange("/api/usuario", HttpMethod.POST,
				httpEntityEdit, tipoRetornoEdit);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(respostaEdit.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		usuario = respostaEdit.getBody().getData();
		assertNotNull(usuario);
		assertEquals(usuario.getLogin(), loginNovo);
		assertEquals(usuario.getPessoa().getEmail(), "mudou.mudou@gmail.com");

	}

	@Test
	public void test08_deletaUsuarioPorObjetoEPorCodigo() {
		Usuario usuarioDeletar = criaUsuario(USUARIO_DELETAR_LOGIN, USUARIO_DELETAR_SENHA, Perfil.ROLE_USUARIO,
				pessoas.get(2));
		HttpEntity<Object> httpEntity = auth.autorizar(usuarioDeletar, restTemplate);
		ParameterizedTypeReference<Response<Usuario>> tipoRetorno = new ParameterizedTypeReference<Response<Usuario>>() {
		};

		ResponseEntity<Response<Usuario>> respostaInsereDeletar = restTemplate.exchange("/api/usuario", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		usuarioDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(usuarioDeletar);

		HttpEntity<String> httpEntityDelete = auth.autorizar(restTemplate);
		ResponseEntity<Response<Usuario>> respostaDelete = restTemplate.exchange("/api/usuario/id/{id}",
				HttpMethod.DELETE, httpEntityDelete, tipoRetorno, 3);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());

		usuarioDeletar = criaUsuario(USUARIO_DELETAR_LOGIN, USUARIO_DELETAR_SENHA, Perfil.ROLE_USUARIO,
				pessoas.get(2));
		respostaInsereDeletar = restTemplate.exchange("/api/usuario", HttpMethod.POST, httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		usuarioDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(usuarioDeletar);

		respostaDelete = restTemplate.exchange("/api/usuario/id/{id}", HttpMethod.DELETE, httpEntityDelete, tipoRetorno,
				4);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());
	}
}
