package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.api.dto.LocalDTO;

/**
 *	1 de dez de 2019
 *
 *	@author gregory.feijon
 */
public class LocalDTOBuilder {

	private LocalDTO localDTO = new LocalDTO();

	public void novo() {
		this.localDTO = new LocalDTO();
	}

	public LocalDTO build() {
		return localDTO;
	}

	public LocalDTOBuilder withId(Long id) {
		localDTO.setId(id);
		return this;
	}
	
	public LocalDTOBuilder withUsuario(Usuario usuario) {
		localDTO.setUsuario(usuario);
		return this;
	}
	
	public LocalDTOBuilder withEndereco(Endereco endereco) {
		localDTO.setEndereco(endereco);
		return this;
	}
	
	public LocalDTOBuilder withLocal(Local local) {
		localDTO.setLocal(local);
		return this;
	}
	
	public LocalDTOBuilder withLocais(List<Local> locais) {
		localDTO.setLocais(locais);
		return this;
	}
}
