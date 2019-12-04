package br.com.ifsp.garageando.model;

import java.util.List;

import br.com.ifsp.garageando.api.dto.EventoDTO;
import br.com.ifsp.garageando.api.dto.WsDTO;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */
public class EventoDTOBuilder<T> extends WsDTO<T> {

	private EventoDTO<T> eventoDTO = new EventoDTO<>();

	public void novo() {
		this.eventoDTO = new EventoDTO<>();
	}

	public EventoDTO<T> build() {
		return eventoDTO;
	}

	public EventoDTOBuilder<T> withId(Long id) {
		eventoDTO.setId(id);
		return this;
	}

	public EventoDTOBuilder<T> withUsuario(Usuario usuario) {
		eventoDTO.setUsuario(usuario);
		return this;
	}

	public EventoDTOBuilder<T> withEndereco(Endereco endereco) {
		eventoDTO.setEndereco(endereco);
		return this;
	}

	public EventoDTOBuilder<T> withEvento(T evento) {
		eventoDTO.setEntity(evento);
		return this;
	}

	public EventoDTOBuilder<T> withEventos(List<T> locais) {
		eventoDTO.setEntities(locais);
		return this;
	}
}
