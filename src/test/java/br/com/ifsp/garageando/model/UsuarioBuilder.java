package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.security.enums.Perfil;

/**
 *	27 de nov de 2019
 *
 *	@author gregory.feijon
 */

public class UsuarioBuilder {

	private Usuario usuario = new Usuario();

	public void novo() {
		this.usuario = new Usuario();
	}

	public Usuario build() {
		return usuario;
	}

	public UsuarioBuilder withLogin(String login) {
		usuario.setLogin(login);
		return this;
	}

	public UsuarioBuilder withSenha(String senha) {
		usuario.setSenha(senha);
		return this;
	}

	public UsuarioBuilder withPerfil(Perfil perfil) {
		usuario.setPerfil(perfil);
		return this;
	}

//	public UsuarioBuilder withEventos(List<Evento> eventos) {
//		usuario.setEventos(eventos);
//		return this;
//	}

	public UsuarioBuilder withPessoa(PessoaFisica pessoaFisica) {
		usuario.setPessoa(pessoaFisica);
		return this;
	}

	public UsuarioBuilder withLocaisProprios(List<Local> locaisProprios) {
		usuario.setLocaisProprios(locaisProprios);
		return this;
	}
	
	public UsuarioBuilder withLocaisFavoritos(List<Local> locaisFavoritos) {
		usuario.setLocaisFavoritos(locaisFavoritos);
		return this;
	}
}
