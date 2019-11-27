package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.security.enums.Perfil;

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

	public UsuarioBuilder withLocaisFavoritos(List<Local> locaisFavoritos) {
		usuario.setLocaisFavoritos(locaisFavoritos);
		return this;
	}

	public UsuarioBuilder withAvaliacao(Avaliacao avaliacao) {
		usuario.setAvaliacao(avaliacao);
		return this;
	}

	public UsuarioBuilder withEvento(Evento evento) {
		usuario.setEvento(evento);
		return this;
	}

	public UsuarioBuilder withPessoa(PessoaFisica pessoaFisica) {
		usuario.setPessoa(pessoaFisica);
		return this;
	}

	public UsuarioBuilder withLocal(Local local) {
		usuario.setLocal(local);
		return this;
	}
}
