package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;

/**
 * 27 de nov de 2019
 *
 * @author gregory.feijon
 */
public class UsuarioDTOBuilder {

	private UsuarioDTO usuarioDTO = new UsuarioDTO();

	public void novo() {
		this.usuarioDTO = new UsuarioDTO();
	}

	public UsuarioDTO build() {
		return usuarioDTO;
	}

	public UsuarioDTOBuilder withLogin(String login) {
		usuarioDTO.setLogin(login);
		return this;
	}

	public UsuarioDTOBuilder withSenha(String senha) {
		usuarioDTO.setSenha(senha);
		return this;
	}

	public UsuarioDTOBuilder withUsuario(Usuario usuario) {
		usuarioDTO.setUsuario(usuario);
		return this;
	}

	public UsuarioDTOBuilder withUsuarios(List<Usuario> usuarios) {
		usuarioDTO.setUsuarios(usuarios);
		return this;
	}
}
