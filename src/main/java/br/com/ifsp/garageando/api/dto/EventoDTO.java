package br.com.ifsp.garageando.api.dto;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 * @param <T>
 */
public class EventoDTO<T> extends WsDTO<T> {

	private Endereco endereco;
	private Usuario usuario;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
