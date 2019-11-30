package br.com.ifsp.garageando.api.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
	
	@Test
	public void test00_CleanUp() {
		testHelper.limpaBanco();
		testHelper.criaUsuarioAdmin();
		testHelper.criarUsuarios();
	}
}
