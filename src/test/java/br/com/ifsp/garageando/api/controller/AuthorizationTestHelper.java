package br.com.ifsp.garageando.api.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import br.com.ifsp.garageando.util.StringUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthorizationTestHelper<T> {

	private String token;

	/**
	 * Método para gerar o token de autorização a ser utilizado nas requisições de
	 * teste.
	 * 
	 * @param restTemplate TestRestTemplate
	 * @return String
	 */
	private String gerarToken(TestRestTemplate restTemplate) {
		// desenvolver parte de tokenização com usuário
		return token;
	}

	/**
	 * Método de autorização para requisições sem body.
	 * 
	 * @param restTemplate TestRestTemplate
	 * @return HttpEntity&ltString&gt
	 */
	public HttpEntity<String> autorizar(TestRestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, StringUtil.isNull(token) ? gerarToken(restTemplate) : token);
		HttpEntity<String> request = new HttpEntity<>(headers);
		return request;
	}

	/**
	 * Método de autorização para requisições com body;
	 * 
	 * @param entity       T
	 * @param restTemplate TestRestTemplate
	 * @return HttpEntity&ltT&gt
	 */
	public HttpEntity<T> autorizar(T entity, TestRestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, StringUtil.isNull(token) ? gerarToken(restTemplate) : token);
		HttpEntity<T> request = new HttpEntity<>(entity, headers);
		return request;
	}
}
