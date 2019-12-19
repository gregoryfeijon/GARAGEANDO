package br.com.ifsp.garageando.api.dto;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 * @param <T>
 */

public class UsuarioDTO<T> extends WsDTO<T> {

	private String login;
	private String senha;
	private String email;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
