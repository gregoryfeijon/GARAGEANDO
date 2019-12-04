package br.com.ifsp.garageando.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.model.EventoBuilder;
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

	public static final String EVENTO_COMPLEMENTO_ALTERADO = "COMPLEMENTO";
	public static final String EVENTO_NOME_ALTERADO = "MUDOU NOME";

	@Autowired
	private EventoService eventoService;

	@Autowired
	private TestHelper testHelper;

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
	public void test01_cadastrarEvento() {
		Evento evento = criarEvento(usuarios.get(0), enderecos.get(0), TestHelper.NOME_EVENTO1, TestHelper.DATA_EVENTO);
		Optional<Evento> opEvento = eventoService.save(evento);
		assertTrue(opEvento.isPresent());
		evento = opEvento.get();
		assertNotNull(evento);
	}

	@Test
	public void test02_alterarEvento() {
		Optional<Evento> opEvento = eventoService.findById(1L);
		assertTrue(opEvento.isPresent());

		Evento evento = opEvento.get();
		assertNotNull(evento);
		evento.setComplemento(EVENTO_COMPLEMENTO_ALTERADO);
		evento.setNome(EVENTO_NOME_ALTERADO);
		evento.setEnderecoEvento(enderecos.get(1));
		opEvento = eventoService.save(evento);
		assertTrue(opEvento.isPresent());
		evento = opEvento.get();
		assertNotNull(evento);
		enderecos.set(1, evento.getEnderecoEvento());
		assertEquals(evento.getComplemento(), EVENTO_COMPLEMENTO_ALTERADO);
		assertEquals(evento.getNome(), EVENTO_NOME_ALTERADO);
		assertEquals(evento.getEnderecoEvento().getId(), enderecos.get(1).getId());
	}

	@Test
	public void test03_findEventoByIdAndExistsById() {
		assertTrue(eventoService.existsById(1L));
		Optional<Evento> opEvento = eventoService.findById(1L);
		assertTrue(opEvento.isPresent());
		Evento evento = opEvento.get();
		assertNotNull(evento);
	}

	@Test
	public void test04_findEventosByUsuario() {
		List<Evento> locais = eventoService.findLocaisByUsuario(usuarios.get(0));
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}

	@Test
	public void test05_findEventosByEndereco() {
		List<Evento> locais = eventoService.findLocaisByEndereco(enderecos.get(1));
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}

	@Test
	public void test06_findAllUsuarios() {
		List<Evento> locais = eventoService.findAll();
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Evento evento = locais.get(0);
		assertNotNull(evento);
	}
}
