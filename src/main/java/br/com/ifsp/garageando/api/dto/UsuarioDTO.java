package br.com.ifsp.garageando.api.dto;

import java.util.List;

import br.com.ifsp.garageando.model.Usuario;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public class UsuarioDTO {

	private Long id;
	private String login;
	private String senha;
	private Usuario usuario;
	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
