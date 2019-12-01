package br.com.ifsp.garageando.api.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.FaixaHorarioDisponivel;
import br.com.ifsp.garageando.model.Local;
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

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TestHelper testHelper;

	AuthorizationTestHelper<Object> auth = new AuthorizationTestHelper<>();

	private static List<Usuario> usuarios;
	private static List<Endereco> enderecos;
	private static List<FaixaHorarioDisponivel> faixasDeHorario;

	private Local criaLocal(Usuario usuario, Endereco endereco, FaixaHorarioDisponivel faixaHorarioDisponivel,
			int localNumero, Double localPrecoMedioHora) {
		// TODO Auto-generated method stub
		return null;
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
		Local local = criaLocal(usuarios.get(0), enderecos.get(0), faixasDeHorario.get(0), TestHelper.LOCAL_NUMERO,
				TestHelper.LOCAL_PRECO_MEDIO_HORA);
	}
}
