package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.api.dto.LocalDTO;
import br.com.ifsp.garageando.api.dto.WsDTO;

/**
 *	1 de dez de 2019
 *
 *	@author gregory.feijon
 * @param <T>
 */

public class LocalDTOBuilder<T> extends WsDTO<T> {

	private LocalDTO<T> localDTO = new LocalDTO<>();

	public void novo() {
		this.localDTO = new LocalDTO<>();
	}

	public LocalDTO<T> build() {
		return localDTO;
	}

	public LocalDTOBuilder<T> withId(Long id) {
		localDTO.setId(id);
		return this;
	}
	
	public LocalDTOBuilder<T> withUsuario(Usuario usuario) {
		localDTO.setUsuario(usuario);
		return this;
	}
	
	public LocalDTOBuilder<T> withEndereco(Endereco endereco) {
		localDTO.setEndereco(endereco);
		return this;
	}
	
	public LocalDTOBuilder<T> withLocal(T local) {
		localDTO.setEntity(local);
		return this;
	}
	
	public LocalDTOBuilder<T> withLocais(List<T> locais) {
		localDTO.setEntities(locais);
		return this;
	}
}
