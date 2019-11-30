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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.model.UsuarioBuilder;
import br.com.ifsp.garageando.security.enums.Perfil;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServiceLogicTest {

	private static final String USUARIO_LOGIN_NOVO = "xobletson";
	private static final String USUARIO_NOME_NOVO = "xoblesflinston";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Autowired
	private TestHelper testHelper;

	private static List<PessoaFisica> pessoas;

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		pessoas = testHelper.criarPessoas(false);
	}

	@Test
	public void test01_CadastrarUsuarios() {
		pessoas.stream().forEach(pessoa -> {
			UsuarioBuilder usuarioBuilder = new UsuarioBuilder().withLogin(TestHelper.USUARIO_LOGIN + Math.random())
					.withSenha(bCryptEncoder.encode(TestHelper.USUARIO_SENHA + Math.random())).withPerfil(Perfil.ROLE_USUARIO)
					.withPessoa(pessoa);
			Usuario usuario = usuarioBuilder.build();

			Optional<Usuario> opUsuario = usuarioService.save(usuario);
			assertTrue(opUsuario.isPresent());
			usuario = opUsuario.get();
			assertNotNull(usuario);
		});
	}

	@Test
	public void test02_AlterarUsuario() {
		Optional<Usuario> opUsuario = usuarioService.findById(2L);
		assertTrue(opUsuario.isPresent());

		Usuario usuario = opUsuario.get();
		assertNotNull(usuario);
		usuario.getPessoa().setNome(USUARIO_NOME_NOVO);
		usuario.setLogin(USUARIO_LOGIN_NOVO);
		opUsuario = usuarioService.save(usuario);
		assertTrue(opUsuario.isPresent());
		usuario = opUsuario.get();
		assertNotNull(usuario);
		assertEquals(usuario.getPessoa().getNome(), USUARIO_NOME_NOVO);
		assertEquals(usuario.getLogin(), USUARIO_LOGIN_NOVO);
	}

	@Test
	public void test03_findUsuarioByIdAndExistsById() {
		assertTrue(usuarioService.existsById(2L));
		Optional<Usuario> opUsuario = usuarioService.findById(2L);
		assertTrue(opUsuario.isPresent());
		Usuario usuario = opUsuario.get();
		assertNotNull(usuario);
	}

	@Test
	public void test04_findUsuarioByLogin() {
		Optional<Usuario> opUsuario = usuarioService.findUsuarioByLogin(USUARIO_LOGIN_NOVO);
		assertTrue(opUsuario.isPresent());
		Usuario usuario = opUsuario.get();
		assertNotNull(usuario);
	}

	@Test
	public void test05_findUsuarioByEmail() {
		Optional<Usuario> opUsuario = usuarioService.findUsuarioByEmail(pessoas.get(0).getEmail());
		assertTrue(opUsuario.isPresent());
		Usuario usuario = opUsuario.get();
		assertNotNull(usuario);
		assertEquals(pessoas.get(0).getEmail(), usuario.getPessoa().getEmail());
	}

	@Test
	public void test06_findAllUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		assertNotNull(usuarios);
		assertTrue(usuarios.size() == pessoas.size() + 1);
		Usuario usuario = usuarios.get(0);
		assertNotNull(usuario);
	}
}
