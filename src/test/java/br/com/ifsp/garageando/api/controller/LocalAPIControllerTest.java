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

import br.com.ifsp.garageando.api.dto.LocalDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.FaixaHorarioDisponivel;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.model.LocalBuilder;
import br.com.ifsp.garageando.model.LocalDTOBuilder;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.service.TestHelper;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAPIControllerTest {

	private static final int NUMERO_ALTERADO = 123;
	private static final boolean DISPONIVEL_ALTERADO = false;
	private static final Double LARGURA_ALTERADO = 20D;
	private static final Double ALTURA_ALTERADO = 10D;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TestHelper testHelper;

	AuthorizationTestHelper<Object> auth = new AuthorizationTestHelper<>();

	private static List<Usuario> usuarios;
	private static List<Endereco> enderecos;
	private static List<FaixaHorarioDisponivel> faixasDeHorario;

	private Local criarLocal(Usuario usuario, Endereco endereco, int localNumero, Double localPrecoMedioHora) {
		LocalBuilder localBuilder = new LocalBuilder().withNumero(localNumero).withPrecoMedioHora(localPrecoMedioHora)
				.withUsuarioProprietario(usuario).withEnderecoLocal(endereco)
				.withFaixasHorariosDisponiveis(faixasDeHorario);
		Local local = localBuilder.build();
		return local;
	}

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		usuarios = testHelper.criarUsuarios();
		enderecos = testHelper.criarEnderecos();
		faixasDeHorario = testHelper.criarFaixasDeHorario();
	}

	@Test
	public void test01_insereLocal() {
		Local local = criarLocal(usuarios.get(0), enderecos.get(0), TestHelper.LOCAL_NUMERO,
				TestHelper.LOCAL_PRECO_MEDIO_HORA);

		HttpEntity<Object> httpEntity = auth.autorizar(local, restTemplate);
		ParameterizedTypeReference<Response<Local>> tipoRetorno = new ParameterizedTypeReference<Response<Local>>() {
		};

		ResponseEntity<Response<Local>> resposta = restTemplate.exchange("/api/local", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Local retorno = resposta.getBody().getData();
		assertNotNull(retorno);
		enderecos.set(0, retorno.getEnderecoLocal());
	}

	@Test
	public void test02_tentaInserirLocalSemEndereco() {
		Local local = criarLocal(usuarios.get(1), null, TestHelper.LOCAL_NUMERO, TestHelper.LOCAL_PRECO_MEDIO_HORA);

		HttpEntity<Object> httpEntity = auth.autorizar(local, restTemplate);
		ResponseEntity<String> resposta = restTemplate.exchange("/api/local", HttpMethod.POST, httpEntity,
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("Erro! É necessário especificar um endereço!")
				|| resposta.getBody().contains("ATENÇÃO! é OBRIGATÓRIO que um local possua um endereço!"));
	}

	@Test
	public void test03_tentaInserirLocalSemUsuario() {
		Local local = criarLocal(null, enderecos.get(1), TestHelper.LOCAL_NUMERO, TestHelper.LOCAL_PRECO_MEDIO_HORA);

		HttpEntity<Object> httpEntity = auth.autorizar(local, restTemplate);
		ResponseEntity<String> resposta = restTemplate.exchange("/api/local", HttpMethod.POST, httpEntity,
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("Erro! É necessário especificar um usuário!")
				|| resposta.getBody().contains("ATENÇÃO! O campo referente a usuário é OBRIGATÓRIO!"));
	}

	@Test
	public void test04_tentaInserirLocalSemFaixaDeHorario() {
		LocalBuilder localBuilder = new LocalBuilder().withNumero(TestHelper.LOCAL_NUMERO)
				.withPrecoMedioHora(TestHelper.LOCAL_PRECO_MEDIO_HORA).withUsuarioProprietario(usuarios.get(1))
				.withEnderecoLocal(enderecos.get(1)).withFaixasHorariosDisponiveis(null);
		Local local = localBuilder.build();

		HttpEntity<Object> httpEntity = auth.autorizar(local, restTemplate);
		ParameterizedTypeReference<Response<Local>> tipoRetorno = new ParameterizedTypeReference<Response<Local>>() {
		};

		ResponseEntity<Response<Local>> resposta = restTemplate.exchange("/api/local", HttpMethod.POST, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().getErrors()
				.contains("Erro! É necessário especificar pelo menos uma faixa de horário!"));
	}

	@Test
	public void test05_findLocalById() {
		LocalDTOBuilder localDTOBuilder = new LocalDTOBuilder().withId(1L);
		LocalDTO localDTO = localDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(localDTO, restTemplate);
		ParameterizedTypeReference<Response<LocalDTO>> tipoRetorno = new ParameterizedTypeReference<Response<LocalDTO>>() {
		};

		ResponseEntity<Response<LocalDTO>> resposta = restTemplate.exchange("/api/local/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		Local local = resposta.getBody().getData().getLocal();
		assertNotNull(local);
		assertEquals(local.getId(), 1L);
	}

	@Test
	public void test06_findLocalByUsuario() {
		LocalDTOBuilder localDTOBuilder = new LocalDTOBuilder().withUsuario(usuarios.get(0));
		LocalDTO localDTO = localDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(localDTO, restTemplate);
		ParameterizedTypeReference<Response<LocalDTO>> tipoRetorno = new ParameterizedTypeReference<Response<LocalDTO>>() {
		};

		ResponseEntity<Response<LocalDTO>> resposta = restTemplate.exchange("/api/local/usuario", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		List<Local> locais = resposta.getBody().getData().getLocais();
		assertNotNull(locais);
		Local local = locais.get(0);
		assertNotNull(local);
	}

	@Test
	public void test07_findLocalByEndereco() {
		LocalDTOBuilder localDTOBuilder = new LocalDTOBuilder().withEndereco(enderecos.get(0));
		LocalDTO localDTO = localDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(localDTO, restTemplate);
		ParameterizedTypeReference<Response<LocalDTO>> tipoRetorno = new ParameterizedTypeReference<Response<LocalDTO>>() {
		};

		ResponseEntity<Response<LocalDTO>> resposta = restTemplate.exchange("/api/local/endereco", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		List<Local> locais = resposta.getBody().getData().getLocais();
		assertNotNull(locais);
		Local local = locais.get(0);
		assertNotNull(local);
	}

	@Test
	public void test08_listAllUsuarios() {
		HttpEntity<String> httpEntity = auth.autorizar(restTemplate);
		ParameterizedTypeReference<List<Local>> tipoRetorno = new ParameterizedTypeReference<List<Local>>() {
		};
		ResponseEntity<List<Local>> response = restTemplate.exchange("/api/local", HttpMethod.GET, httpEntity,
				tipoRetorno);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertTrue(response.getBody().size() != 0);
		List<Local> locais = response.getBody();
		Local local = locais.get(0);
		assertNotNull(local);
	}

	@Test
	public void test09_AlteraLocal() {
		LocalDTOBuilder localDTOBuilder = new LocalDTOBuilder().withId(1L);
		LocalDTO localDTO = localDTOBuilder.build();
		HttpEntity<Object> httpEntity = auth.autorizar(localDTO, restTemplate);
		ParameterizedTypeReference<Response<LocalDTO>> tipoRetorno = new ParameterizedTypeReference<Response<LocalDTO>>() {
		};

		ResponseEntity<Response<LocalDTO>> resposta = restTemplate.exchange("/api/local/id", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		assertNotNull(resposta.getBody().getData().getLocal());
		Local local = resposta.getBody().getData().getLocal();
		local.setAltura(ALTURA_ALTERADO);
		local.setLargura(LARGURA_ALTERADO);
		local.setDisponivel(DISPONIVEL_ALTERADO);
		local.setNumero(NUMERO_ALTERADO);
		local.setEnderecoLocal(enderecos.get(1));

		HttpEntity<Object> httpEntityEdit = auth.autorizar(local, restTemplate);
		ParameterizedTypeReference<Response<Local>> tipoRetornoEdit = new ParameterizedTypeReference<Response<Local>>() {
		};

		ResponseEntity<Response<Local>> respostaEdit = restTemplate.exchange("/api/local", HttpMethod.PUT,
				httpEntityEdit, tipoRetornoEdit);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(respostaEdit.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		local = respostaEdit.getBody().getData();
		assertNotNull(local);
		assertEquals(local.getAltura(), ALTURA_ALTERADO);
		assertEquals(local.getLargura(), LARGURA_ALTERADO);
		assertEquals(local.isDisponivel(), DISPONIVEL_ALTERADO);
		assertEquals(local.getNumero(), NUMERO_ALTERADO);
		assertEquals(local.getEnderecoLocal().getRua(), enderecos.get(1).getRua());
	}

	@Test
	public void test10_deletaLocalPorObjetoECodigo() {
		Local localDeletar = criarLocal(usuarios.get(2), enderecos.get(2), TestHelper.LOCAL_NUMERO,
				TestHelper.LOCAL_PRECO_MEDIO_HORA);
		HttpEntity<Object> httpEntity = auth.autorizar(localDeletar, restTemplate);
		ParameterizedTypeReference<Response<Local>> tipoRetorno = new ParameterizedTypeReference<Response<Local>>() {
		};

		ResponseEntity<Response<Local>> respostaInsereDeletar = restTemplate.exchange("/api/local", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		localDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(localDeletar);

		HttpEntity<String> httpEntityDelete = auth.autorizar(restTemplate);
		ResponseEntity<Response<Local>> respostaDelete = restTemplate.exchange("/api/local/id/{id}", HttpMethod.DELETE,
				httpEntityDelete, tipoRetorno, 2);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());

		localDeletar = criarLocal(usuarios.get(2), enderecos.get(2), TestHelper.LOCAL_NUMERO,
				TestHelper.LOCAL_PRECO_MEDIO_HORA);
		respostaInsereDeletar = restTemplate.exchange("/api/local", HttpMethod.POST, httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, respostaInsereDeletar.getStatusCode());
		assertTrue(respostaInsereDeletar.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
		localDeletar = respostaInsereDeletar.getBody().getData();
		assertNotNull(localDeletar);

		HttpEntity<Object> httpEntityDelete2 = auth.autorizar(localDeletar, restTemplate);
		respostaDelete = restTemplate.exchange("/api/local", HttpMethod.DELETE, httpEntityDelete2, tipoRetorno);
		assertEquals(HttpStatus.NO_CONTENT, respostaDelete.getStatusCode());
	}
}
