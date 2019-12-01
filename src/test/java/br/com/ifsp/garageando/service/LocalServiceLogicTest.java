package br.com.ifsp.garageando.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import br.com.ifsp.garageando.model.FaixaHorarioDisponivel;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.model.LocalBuilder;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalServiceLogicTest {

	@Autowired
	private LocalService localService;

	@Autowired
	private TestHelper testHelper;

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
	public void test01_CadastrarLocal() {
		Local local = criarLocal(usuarios.get(0), enderecos.get(0), TestHelper.LOCAL_NUMERO,
				TestHelper.LOCAL_PRECO_MEDIO_HORA);
		Optional<Local> opLocal = localService.save(local);
		assertTrue(opLocal.isPresent());
		local = opLocal.get();
		assertNotNull(local);
	}

	@Test
	public void test02_AlterarLocal() {
		Optional<Local> opLocal = localService.findById(1L);
		assertTrue(opLocal.isPresent());

		Local local = opLocal.get();
		assertNotNull(local);
		local.setAltura(2D);
		local.setLargura(5D);
		local.setEnderecoLocal(enderecos.get(1));
		opLocal = localService.save(local);
		assertTrue(opLocal.isPresent());
		local = opLocal.get();
		assertNotNull(local);
		enderecos.set(1, local.getEnderecoLocal());
		assertEquals(local.getAltura(), 2D, 0D);
		assertEquals(local.getLargura(), 5D, 0D);
		assertEquals(local.getEnderecoLocal().getRua(), enderecos.get(1).getRua());
	}

	@Test
	public void test03_findLocalByIdAndExistsById() {
		assertTrue(localService.existsById(1L));
		Optional<Local> opLocal = localService.findById(1L);
		assertTrue(opLocal.isPresent());
		Local local = opLocal.get();
		assertNotNull(local);
	}

	@Test
	public void test04_findLocaisByUsuario() {
		List<Local> locais = localService.findLocaisByUsuario(usuarios.get(0));
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Local local = locais.get(0);
		assertNotNull(local);
	}

	@Test
	public void test05_findLocaisByEndereco() {
		List<Local> locais = localService.findLocaisByEndereco(enderecos.get(1));
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Local local = locais.get(0);
		assertNotNull(local);
	}

	@Test
	public void test06_findAllUsuarios() {
		List<Local> locais = localService.findAll();
		assertNotNull(locais);
		assertTrue(locais.size() != 0);
		Local local = locais.get(0);
		assertNotNull(local);
	}
}
