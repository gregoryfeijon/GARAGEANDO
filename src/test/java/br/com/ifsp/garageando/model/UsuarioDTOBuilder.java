package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;
import br.com.ifsp.garageando.api.dto.WsDTO;

/**
 * 27 de nov de 2019
 *
 * @author gregory.feijon
 * @param <T>
 */

public class UsuarioDTOBuilder<T> extends WsDTO<T> {

	private UsuarioDTO<T> usuarioDTO = new UsuarioDTO<>();

	public void novo() {
		this.usuarioDTO = new UsuarioDTO<>();
	}

	public UsuarioDTO<T> build() {
		return usuarioDTO;
	}

	public UsuarioDTOBuilder<T> withId(Long id) {
		usuarioDTO.setId(id);
		return this;
	}

	public UsuarioDTOBuilder<T> withLogin(String login) {
		usuarioDTO.setLogin(login);
		return this;
	}

	public UsuarioDTOBuilder<T> withSenha(String senha) {
		usuarioDTO.setSenha(senha);
		return this;
	}

	public UsuarioDTOBuilder<T> withEmail(String email) {
		usuarioDTO.setEmail(email);
		return this;
	}

	public UsuarioDTOBuilder<T> withUsuario(T usuario) {
		usuarioDTO.setEntity(usuario);
		return this;
	}

	public UsuarioDTOBuilder<T> withUsuarios(List<T> usuarios) {
		usuarioDTO.setEntities(usuarios);
		return this;
	}
}
