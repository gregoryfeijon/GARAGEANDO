package br.com.ifsp.garageando.api.dto;

import java.util.List;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 * @param <T>
 */

public abstract class WsDTO<T> {

	private Long id;
	private T entity;
	private List<T> entities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
}
