package br.com.ifsp.garageando.service;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoServiceLogicTest {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private TestHelper testHelper;

	private static List<Usuario> usuarios;
	private static List<Endereco> enderecos;

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		usuarios = testHelper.criarUsuarios();
		enderecos = testHelper.criarEnderecos();
	}

	@Test
	public void test01_cadastrarEvento() {
		
	}
}
