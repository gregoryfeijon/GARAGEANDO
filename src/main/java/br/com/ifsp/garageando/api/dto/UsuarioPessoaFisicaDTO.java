package br.com.ifsp.garageando.api.dto;

import java.util.List;

import br.com.ifsp.garageando.model.UsuarioPessoaFisica;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public class UsuarioPessoaFisicaDTO {

	private Long id;
	private UsuarioPessoaFisica usuario;
	private List<UsuarioPessoaFisica> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioPessoaFisica getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPessoaFisica usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioPessoaFisica> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioPessoaFisica> usuarios) {
		this.usuarios = usuarios;
	}
}
