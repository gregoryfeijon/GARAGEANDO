package br.com.ifsp.garageando.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
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

import br.com.ifsp.garageando.api.dto.EventoDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.model.EventoBuilder;
import br.com.ifsp.garageando.model.EventoDTOBuilder;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.service.EventoServiceLogicTest;
import br.com.ifsp.garageando.service.TestHelper;

/**
 * 3 de dez de 2019
 *
 * @author gregory.feijon
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoAPIControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TestHelper testHelper;

	AuthorizationTestHelper<Object> auth = new AuthorizationTestHelper<>();

	private static List<Usuario> usuarios;
	private static List<Endereco> enderecos;

	private Evento criarEvento(Usuario usuario, Endereco endereco, String nomeEvento, LocalDate dataEvento) {
		EventoBuilder eventoBuilder = new EventoBuilder().withNome(nomeEvento).withData(dataEvento)
				.withEnderecoEvento(endereco).withUsuarioResponsavelEvento(usuario);
		return eventoBuilder.build();
	}

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		usuarios = testHelper.criarUsuarios();
		enderecos = testHelper.criarEnderecos();
	}

	@Test
	public void test01_insereEvento() {
		Evento evento = criarEvento(usuarios.get(0), enderecos.get(0), TestHelper.NOME_EVENTO1, TestHelper.DATA_EVENTO);

		HttpEntity<Object> httpEntity = auth.autorizar(evento, restTemplate);
		ParameterizedTypeReference<Response<Evento>> tipoRetorno = new ParameterizedTypeReference<Response<Evento>>() {
		};

		ResponseEntity<Response<Evento>> resposta = restTemplate.exchange("/api/evento", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Evento retorno = resposta.getBody().getData();
		assertNotNull(retorno);
		enderecos.set(0, retorno.getEnderecoEvento());
	}

	@Test
	public void test02_tentaInserirEventoSemEndereco() {
		Evento evento = criarEvento(usuarios.get(1), null, TestHelper.NOME_EVENTO1, TestHelper.DATA_EVENTO);

		HttpEntity<Object> httpEntity = auth.autorizar(evento, restTemplate);
		ResponseEntity<String> resposta = restTemplate.exchange("/api/evento", HttpMethod.POST, httpEntity,
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		System.out.println(resposta.getBody());
		assertTrue(resposta.getBody().contains("Erro! É necessário especificar um endereço!")
				|| resposta.getBody().contains("ATENÇÃO! é OBRIGATÓRIO que um evento possua um endereço!"));
	}

	@Test
	public void test03_tentaInserirEventoSemUsuario() {
		Evento evento = criarEvento(null, enderecos.get(1), TestHelper.NOME_EVENTO1, TestHelper.DATA_EVENTO);

		HttpEntity<Object> httpEntity = auth.autorizar(evento, restTemplate);
		ResponseEntity<String> resposta = restTemplate.exchange("/api/evento", HttpMethod.POST, httpEntity,
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("Erro! É necessário especificar um usuário!")
				|| resposta.getBody().contains("ATENÇÃO! O campo referente a usuário é OBRIGATÓRIO!"));
	}

	@Test
	public void test04_findEventoById() {
		EventoDTOBuilder<Evento> eventoDTOBuilder = new EventoDTOBuilder<Evento>().withId(1L);
		EventoDTO<Evento> eventoDTO = eventoDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(eventoDTO, restTemplate);
		ParameterizedTypeReference<Response<EventoDTO<Evento>>> tipoRetorno = new ParameterizedTypeReference<Response<EventoDTO<Evento>>>() {
		};

		ResponseEntity<Response<EventoDTO<Evento>>> resposta = restTemplate.exchange("/api/evento/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Evento evento = resposta.getBody().getData().getEntity();
		assertNotNull(evento);
		assertEquals(evento.getId(), Long.valueOf(1L));
	}

	@Test
	public void test05_findEventoByUsuario() {
		EventoDTOBuilder<Evento> eventoDTOBuilder = new EventoDTOBuilder<Evento>().withUsuario(usuarios.get(0));
		EventoDTO<Evento> eventoDTO = eventoDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(eventoDTO, restTemplate);
		ParameterizedTypeReference<Response<EventoDTO<Evento>>> tipoRetorno = new ParameterizedTypeReference<Response<EventoDTO<Evento>>>() {
		};

		ResponseEntity<Response<EventoDTO<Evento>>> resposta = restTemplate.exchange("/api/evento/usuario",
				HttpMethod.POST, httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		List<Evento> locais = resposta.getBody().getData().getEntities();
		assertNotNull(locais);
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}

	@Test
	public void test06_findEventoByEndereco() {
		EventoDTOBuilder<Evento> eventoDTOBuilder = new EventoDTOBuilder<Evento>().withEndereco(enderecos.get(0));
		EventoDTO<Evento> eventoDTO = eventoDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(eventoDTO, restTemplate);
		ParameterizedTypeReference<Response<EventoDTO<Evento>>> tipoRetorno = new ParameterizedTypeReference<Response<EventoDTO<Evento>>>() {
		};

		ResponseEntity<Response<EventoDTO<Evento>>> resposta = restTemplate.exchange("/api/evento/endereco",
				HttpMethod.POST, httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		List<Evento> locais = resposta.getBody().getData().getEntities();
		assertNotNull(locais);
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}

	@Test
	public void test07_listAllEventos() {
		HttpEntity<String> httpEntity = auth.autorizar(restTemplate);
		ParameterizedTypeReference<List<Evento>> tipoRetorno = new ParameterizedTypeReference<List<Evento>>() {
		};
		ResponseEntity<List<Evento>> response = restTemplate.exchange("/api/evento", HttpMethod.GET, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertTrue(response.getBody().size() != 0);
		List<Evento> locais = response.getBody();
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}

	@Test
	public void test08_AlteraEvento() {
		EventoDTOBuilder<Evento> eventoDTOBuilder = new EventoDTOBuilder<Evento>().withId(1L);
		EventoDTO<Evento> eventoDTO = eventoDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(eventoDTO, restTemplate);
		ParameterizedTypeReference<Response<EventoDTO<Evento>>> tipoRetorno = new ParameterizedTypeReference<Response<EventoDTO<Evento>>>() {
		};

		ResponseEntity<Response<EventoDTO<Evento>>> resposta = restTemplate.exchange("/api/evento/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertNotNull(resposta.getBody().getData().getEntity());
		Evento evento = resposta.getBody().getData().getEntity();
		evento.setComplemento(EventoServiceLogicTest.EVENTO_COMPLEMENTO_ALTERADO);
		evento.setNome(EventoServiceLogicTest.EVENTO_NOME_ALTERADO);
		evento.setEnderecoEvento(enderecos.get(1));

		HttpEntity<Object> httpEntityEdit = auth.autorizar(evento, restTemplate);
		ParameterizedTypeReference<Response<Evento>> tipoRetornoEdit = new ParameterizedTypeReference<Response<Evento>>() {
		};

		ResponseEntity<Response<Evento>> respostaEdit = restTemplate.exchange("/api/evento", HttpMethod.PUT,
				httpEntityEdit, tipoRetornoEdit);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(respostaEdit.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		evento = respostaEdit.getBody().getData();
		assertNotNull(evento);
		enderecos.set(1, evento.getEnderecoEvento());
		assertEquals(evento.getComplemento(), EventoServiceLogicTest.EVENTO_COMPLEMENTO_ALTERADO);
		assertEquals(evento.getNome(), EventoServiceLogicTest.EVENTO_NOME_ALTERADO);
		assertEquals(evento.getEnderecoEvento().getId(), enderecos.get(1).getId());
	}

	@Test
	public void test09_deletaEventoPorObjetoECodigo() {
		Evento eventoDeletar = criarEvento(usuarios.get(2), enderecos.get(2), TestHelper.NOME_EVENTO2,
				TestHelper.DATA_EVENTO);
		HttpEntity<Object> httpEntity = auth.autorizar(eventoDeletar, restTemplate);
		ParameterizedTypeReference<Response<Evento>> tipoRetorno = new ParameterizedTypeReference<Response<Evento>>() {
		};

		ResponseEntity<Response<Evento>> respostaInsereDeletar = restTemplate.exchange("/api/evento", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		eventoDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(eventoDeletar);

		HttpEntity<String> httpEntityDelete = auth.autorizar(restTemplate);
		ResponseEntity<Response<Evento>> respostaDelete = restTemplate.exchange("/api/evento/id/{id}",
				HttpMethod.DELETE, httpEntityDelete, tipoRetorno, 2);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());

		eventoDeletar = criarEvento(usuarios.get(2), enderecos.get(2), TestHelper.NOME_EVENTO2, TestHelper.DATA_EVENTO);
		respostaInsereDeletar = restTemplate.exchange("/api/evento", HttpMethod.POST, httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		eventoDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(eventoDeletar);

		HttpEntity<Object> httpEntityDelete2 = auth.autorizar(eventoDeletar, restTemplate);
		respostaDelete = restTemplate.exchange("/api/evento", HttpMethod.DELETE, httpEntityDelete2, tipoRetorno);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());
	}
}
