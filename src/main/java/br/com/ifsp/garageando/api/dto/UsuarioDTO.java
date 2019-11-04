package br.com.ifsp.garageando.api.dto;

import java.util.List;

import br.com.ifsp.garageando.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private Usuario usuario;
	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
