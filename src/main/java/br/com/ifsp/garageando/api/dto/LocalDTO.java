package br.com.ifsp.garageando.api.dto;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 * @param <T>
 */

public class LocalDTO<T> extends WsDTO<T> {

	private Usuario usuario;
	private Endereco endereco;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
