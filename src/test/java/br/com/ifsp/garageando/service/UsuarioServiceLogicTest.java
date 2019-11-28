package br.com.ifsp.garageando.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServiceLogicTest {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TestHelper testHelper;

	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
	}

	public void test01_CadastrarUsuario() {
		
	}

	public void test02_AlterarUsuario() {

	}

	public void test03_findUsuarioById() {

	}

	public void test04_findUsuarioByLogin() {

	}

	public void test05_findUsuarioByEmail() {

	}

	public void test06_findAllUsuarios() {

	}
}
